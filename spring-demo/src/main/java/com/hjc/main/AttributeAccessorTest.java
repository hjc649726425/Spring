package com.hjc.main;

import config.Appconfig;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AttributeAccessorTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClassName("com.hjc.service.InterService");
		//设置属性值
		beanDefinition.setAttribute("AttributeAccessor","黄吉超");
		//将beanDefinition注册到spring容器中
		context.registerBeanDefinition("interService3",beanDefinition);
		//加载或者刷新当前的配置信息
		context.refresh();
		//拿到属性信息
		String[] attributes = context.getBeanDefinition("interService3").attributeNames();
		for (String attribute : attributes) {
			System.out.print(attribute + "   ");
		}
		System.out.println();
		System.out.println(context.getBeanDefinition("interService").getSource());
	}
}
