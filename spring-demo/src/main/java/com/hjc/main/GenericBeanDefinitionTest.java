package com.hjc.main;

import config.Appconfig;
import com.hjc.model.User;
import com.hjc.service.InterService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GenericBeanDefinitionTest {
	//GenericBeanDefinition可以作为父bd出现，也可以作为子bd出现。他可以完全替代ChildBeanDefinition，
	// 但不能完全替代RootBeanDefinition
	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		//模板BeanDefinition
		GenericBeanDefinition root = new GenericBeanDefinition();
		//设置为抽象类
		root.setAbstract(true);
		root.setDescription("我是一个模板");
		root.setBeanClass(InterService.class);
		root.getPropertyValues().add("name","源码之路");
		root.setScope(BeanDefinition.SCOPE_PROTOTYPE);
		//注册，放到IOC容器中
		context.registerBeanDefinition("interService",root);

		//child继承root
		GenericBeanDefinition child = new GenericBeanDefinition();
		child.setParentName("interService");
		child.setAbstract(false);
		child.setBeanClass(User.class);
		//注册，放到IOC容器中
		context.registerBeanDefinition("child",child);

		context.refresh();

		System.out.println(((User)context.getBean("child")).getName());
		System.out.println(context.getBeanDefinition("interService").getScope());
	}
}
