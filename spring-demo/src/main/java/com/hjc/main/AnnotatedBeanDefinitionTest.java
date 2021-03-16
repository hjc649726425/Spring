package com.hjc.main;

import config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotatedBeanDefinitionTest {

	//通过AnnotatedBeanDefinitionReader手动注册的类sping都会生成AnnotatedGenericBeanDefinition。
	// 而自动扫描的都会生成ScannedGenericBeanDefinition。
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		context.refresh();

		//手动注册一个业务类
		//context.register(InterService.class);  AnnotatedGenericBeanDefinition
		System.out.println(context.getBeanDefinition("interService").getClass().getSimpleName());
		//ScannedGenericBeanDefinition
		System.out.println();
	}
}
