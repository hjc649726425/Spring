package com.hjc.main;

import config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CGLIB_BeanTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		context.refresh();

		//加了@Configuration是动态代理，否则就不是
		Appconfig appconfig = context.getBean(Appconfig.class);
		System.out.println(appconfig);
	}
}
