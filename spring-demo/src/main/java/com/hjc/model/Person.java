package com.hjc.model;

public class Person {

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				'}';
	}

	public void setName(String name) {
		this.name = name;
	}

	public String name;
}
