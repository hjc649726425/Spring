package com.hjc.model;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

//ImportBeanDefinitionRegistrar接口是也是spring的扩展点之一,
// 它可以支持我们自己写的代码封装成BeanDefinition对象;
// 实现此接口的类会回调postProcessBeanDefinitionRegistry方法，注册到spring容器中。
// 把bean注入到spring容器不止有 @Service @Component等注解方式；还可以实现此接口
public class User implements ImportBeanDefinitionRegistrar {
	private String name;
	private Integer age;

	public User(){}
	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName(User.class.getName());
		MutablePropertyValues values = beanDefinition.getPropertyValues();
		values.addPropertyValue("age", 1);
		values.addPropertyValue("name", "hjc");
		//这里注册bean
		registry.registerBeanDefinition("testUser", beanDefinition );
	}
}