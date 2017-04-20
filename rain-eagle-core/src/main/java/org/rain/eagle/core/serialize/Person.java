package org.rain.eagle.core.serialize;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 8779398196243571209L;

	private String name;

	private transient int age;

	private char sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

}
