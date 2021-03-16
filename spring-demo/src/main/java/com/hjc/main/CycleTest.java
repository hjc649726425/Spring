package com.hjc.main;

import config.Appconfig;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CycleTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		//获取bean工厂
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
		//关闭循环依赖
		//beanFactory.setAllowCircularReferences(false);
		context.register(Appconfig.class);
		context.refresh();

		System.out.println(context.getBean("serviceA"));
	}
}
