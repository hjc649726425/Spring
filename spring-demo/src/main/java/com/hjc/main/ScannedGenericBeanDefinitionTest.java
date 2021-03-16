package com.hjc.main;

import config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

public class ScannedGenericBeanDefinitionTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		context.refresh();
		ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) context.getBeanDefinition("interService");
		//查看spring给我生成的具体BeanDefinition类型
		System.out.println(beanDefinition.getClass().getSimpleName());
		//获取注解信息
		AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
		//获取工厂方法元数据
		MethodMetadata methodMetadata = beanDefinition.getFactoryMethodMetadata();
		System.out.println();

		context.scan("com.hjc");
	}
}
