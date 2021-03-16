package com.hjc.main;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

public class DataBindTest {
	public static void main(String[] args) throws BindException {
		Person person = new Person();
		DataBinder binder = new DataBinder(person, "person");
		// 创建用于绑定到对象上的属性对（属性名称，属性值）
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "fsx");
		pvs.add("age", 18);
		binder.bind(pvs);
		System.out.println(person);
		// 程序打印：Person{name='fsx', age=18}
	}
}

class Person {
	String name;

	int age;

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

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}