package com.hjc.main;

import config.Appconfig;
import com.hjc.service.InterService;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RootChildBeanDefinitionTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);

		//模板BeanDefinition
		RootBeanDefinition root = new RootBeanDefinition();
		//设置为抽象类
		root.setAbstract(true);
		root.setDescription("我是一个模板");
		root.setBeanClass(InterService.class);
		root.getPropertyValues().add("name","源码之路");
		//注册，放到IOC容器中
		context.registerBeanDefinition("interService4",root);

		//child继承root
		ChildBeanDefinition child = new ChildBeanDefinition("interService4");
		//注册，放到IOC容器中
		context.registerBeanDefinition("child",child);
		context.refresh();
		System.out.println(((InterService)context.getBean("child")).getName());
	}
}
