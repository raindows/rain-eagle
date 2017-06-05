package org.rain.eagle.core.serialize;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonJdkSerialize extends Person {

	private static final long serialVersionUID = 7115111018724394554L;

	private Logger LOG = LoggerFactory.getLogger(PersonJdkSerialize.class);

	private void writeObject(ObjectOutputStream oos) throws Exception {
		LOG.info("person serialize");

		oos.defaultWriteObject();
		oos.writeInt(this.getAge());
		
		/*oos.writeChar(this.getSex());
		
		String name = this.getName();
		int length = name.length();
		oos.writeInt(length);
		for(int i = 0; i < length; i++){
			oos.writeChar(name.charAt(i));
		}*/
	}

	private void readObject(ObjectInputStream ois) throws Exception {
		ois.defaultReadObject();
		this.setAge(ois.readInt());
		
		/*this.setSex(ois.readChar());
		
		int length =  ois.readInt();
		char[] chars = new char[length];
		for(int i = 0; i < length; i++){
			chars[i] = ois.readChar();
		}
		this.setName(new String(chars));*/

		LOG.info("person deserialize");
	}
}
