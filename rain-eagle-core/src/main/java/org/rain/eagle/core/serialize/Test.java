package org.rain.eagle.core.serialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static Logger LOG = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws Exception {
		SerializeExecutor<Person> executor = new SerializeExecutor<>(new JdkSerialize<Person>());
		executor.serialize(getPerson(new Person()));
		Person person = executor.deserialize();
		log(person);

		executor.serialize(getPerson(new PersonJdkSerialize()));
		Person personJdk = executor.deserialize();
		log(personJdk);

	}

	private static void log(Person person) {
		// System.out.printf("person name: [%s] sex: [%s] age: [%s] \n",
		// person.getName(), person.getSex(),person.getAge());
		LOG.info("person name: [{}] sex: [{}] age: [{}]", person.getName(), person.getSex(), person.getAge());
	}

	private static Person getPerson(Person person) {
		person.setName("rainy");
		person.setAge(26);
		person.setSex('M');
		return person;
	}

}
