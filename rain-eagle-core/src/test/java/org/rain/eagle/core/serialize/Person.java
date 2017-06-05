package org.rain.eagle.core.serialize;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Person implements Serializable {

	private static final long serialVersionUID = 8779398196243571209L;

	private String name;

	private transient int age;

	private char sex;

	private Map<Long, String> map;

	private List<String> list;

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

	public Map<Long, String> getMap() {
		return map;
	}

	public void setMap(Map<Long, String> map) {
		this.map = map;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + ", map=" + map + ", list=" + list + "]\n";
	}

}
