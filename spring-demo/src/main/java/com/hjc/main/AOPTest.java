package com.hjc.main;

import config.Appconfig;
import com.hjc.service.ServiceA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(Appconfig.class);
		context.refresh();

		ServiceA a = (ServiceA)context.getBean("serviceA");
		a.test();
		System.out.println();
	}
}
