package com.hjc.main;

import config.Appconfig;
import com.hjc.model.User;
import com.hjc.service.IUserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TranTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		//获取bean工厂
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
		context.register(Appconfig.class);
		context.refresh();
		IUserService userService = (IUserService) context.getBean("userService");
		Future<String> login = userService.login();
		System.out.println("isdone:" + login.isDone());
		System.out.println("return :" + login.get());
		System.out.println("isdone:" + login.isDone());
		System.out.println(userService.getClass());
//		IUserService userService1 = (IUserService)context.getBean("userService1");
//		System.out.println(userService1);
//		System.out.println(userService1.getClass());
//		System.out.println(userService == userService1);
//
//		IUserService userService2 = (IUserService)context.getBean("userService2");
//		System.out.println(userService2);
//		System.out.println(userService2.getClass());
//
//		System.out.println(userService.login());
//		System.out.println(userService1.login());
//		System.out.println(userService2.login());

		System.out.println("----------------userFactory----------------");
		//设置了ByName自动装配
		User u = (User) context.getBean("userFactory");
		System.out.println(u);
		System.out.println(context.getBean("userFactory"));

		System.out.println(context.getBean("&userFactory"));

		System.out.println("d1:" + context.getBean("d1"));
	}
}
