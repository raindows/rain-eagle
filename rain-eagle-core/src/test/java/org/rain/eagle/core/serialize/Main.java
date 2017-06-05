package org.rain.eagle.core.serialize;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		jdk();
		jdkOverride();
		fastjson();
		protostuff();
	}

	private static void protostuff() throws Exception {
		SerializeExecutor<Person> executor = new SerializeExecutor<>(new ProtostuffSerialize<Person>());
		byte[] data = executor.doSerialize(getPerson(new Person()));
		LOG.info("protostuff serialize data length: {}", data.length);
		Person person = executor.doDeserialize(data, Person.class);
		log(person);
	}

	private static void fastjson() throws Exception {
		SerializeExecutor<Person> executor = new SerializeExecutor<>(new FastjsonSerialize<Person>());
		byte[] data = executor.doSerialize(getPerson(new Person()));
		LOG.info("fastjson serialize data length: {}", data.length);
		Person person = executor.doDeserialize(data);
		log(person);
		person = executor.doDeserialize(data, Person.class);
		log(person);
	}

	private static void jdkOverride() throws Exception {
		SerializeExecutor<Person> executor = new SerializeExecutor<>(new JdkSerialize<Person>());
		executor.serialize(getPerson(new PersonJdkSerialize()));
		Person personJdk = executor.deserialize();
		log(personJdk);

		byte[] data = executor.doSerialize(getPerson(new PersonJdkSerialize()));
		LOG.info("jdk serialize data length: {}", data.length);
		personJdk = executor.doDeserialize(data);
		log(personJdk);
	}

	private static void jdk() throws Exception {
		SerializeExecutor<Person> executor = new SerializeExecutor<>(new JdkSerialize<Person>());
		executor.serialize(getPerson(new Person()));
		Person person = executor.deserialize();
		log(person);

		byte[] data = executor.doSerialize(getPerson(new Person()));
		LOG.info("jdk serialize data length: {}", data.length);
		person = executor.doDeserialize(data);
		log(person);
	}

	private static void log(Person person) {
		/*System.out.printf("person name: [%s] sex: [%s] age: [%s] \n", person.getName(), person.getSex(),
				person.getAge());
		LOG.info("person name: [{}] sex: [{}] age: [{}]\n", person.getName(), person.getSex(), person.getAge());*/
		LOG.info(person.toString()); 
	}

	private static Person getPerson(Person person) {
		person.setName("rainy");
		person.setAge(26);
		person.setSex('M');
		
		String uuid = UUID.randomUUID().toString();
		List<String> list = new LinkedList<>();
		list.add(uuid);
		person.setList(list);
		
		Map<Long, String> map = new LinkedHashMap<>();
		map.put(Long.MAX_VALUE, uuid);
		person.setMap(map);
		return person;
	}

}
