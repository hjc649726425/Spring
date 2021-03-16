package com.hjc.main;

import config.Appconfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionRegisterTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);

		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName("com.hjc.service.InterService");
		beanDefinition.setScope("singleton");
		beanDefinition.setDescription("手动注入");
		beanDefinition.setAbstract(false);
		//将beanDefinition注册到spring容器中
		context.registerBeanDefinition("interService2",beanDefinition);

		//加载或者刷新当前的配置信息
		context.refresh();



		BeanDefinition interServiceBeanDefinition = context.getBeanDefinition("interService2");
		System.out.println("——————InterService的附加属性如下：");
		System.out.println("父类"+interServiceBeanDefinition.getParentName());
		System.out.println("描述"+interServiceBeanDefinition.getDescription());
		System.out.println("InterService在spring的名称"+interServiceBeanDefinition.getBeanClassName());
		System.out.println("实例范围"+interServiceBeanDefinition.getScope());
		System.out.println("是否是懒加载"+interServiceBeanDefinition.isLazyInit());
		System.out.println("是否是抽象类"+interServiceBeanDefinition.isAbstract());
	}
}
