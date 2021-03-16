package com.hjc.main;

import config.Appconfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		//加载或者刷新当前的配置信息
		context.refresh();;
		//获取InterService对应的BeanDefinition ，默认名称为interService，关于名字的更改以后讲。
		BeanDefinition interServiceBeanDefinition = context.getBeanDefinition("interService");
		//interServiceBeanDefinition = context.getBeanDefinition("xxx");

		System.out.println("——————InterService的附加属性如下：");
		System.out.println("父类"+interServiceBeanDefinition.getParentName());
		System.out.println("描述"+interServiceBeanDefinition.getDescription());
		System.out.println("InterService在spring的名称"+interServiceBeanDefinition.getBeanClassName());
		System.out.println("实例范围"+interServiceBeanDefinition.getScope());
		System.out.println("是否是懒加载"+interServiceBeanDefinition.isLazyInit());
		System.out.println("是否是抽象类"+interServiceBeanDefinition.isAbstract());
	}
}
