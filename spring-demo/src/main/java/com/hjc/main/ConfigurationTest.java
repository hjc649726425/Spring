package com.hjc.main;

import config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
//在@Configuration注解的类中，使用@Bean注解实例化的Bean，
// 其定义会用ConfigurationClassBeanDefinition存储。
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		context.refresh();
		System.out.println(context.getBeanDefinition("getUser").getClass().getSimpleName());
		//ConfigurationClassBeanDefinition
	}
}
