package com.hjc.main;

import config.MvcConfig;
import com.hjc.event.MyApplicationEvent;
import com.hjc.process.mvc.MyWebApplicationContext;
import com.hjc.service.IUserService;

import java.util.concurrent.Future;

public class MvcMain {

	/**
	 1.servlet容器（这里是tomcat）启动时，通过spi机制执行 ServletContainerInitializer#onStartup方法，
	 	而springmvc对提供的SpringServletContainerInitializer对其进行了实现，于是SpringServletContainerInitializer#onStartup方法会被调用；
	 2.SpringServletContainerInitializer#onStartup方法中，spring会调用WebApplicationInitializer#onStartup 方法，
	 	而MyWebApplicationInitializer对其进行了实现，于是MyWebApplicationInitializer#onStartup 会被调用；
	 3.在MyWebApplicationInitializer#onStartup方法中 ，我们创建了一个applicationContext对象，
	 	将其与DispatcherServlet绑定，然后将DispatcherServlet注册到servlet容器中（这里是tomcat）；
	 4.DispatcherServlet注册到servlet容器中（这里是tomcat）后，根据servlet生命周期，DispatcherServlet#init将会被调用；
	 5.DispatcherServlet#init 中会执行spring容器的启动过程，spring容器启动后，会发布启动完成事件；
	 6.spring启动完成后，ContextRefreshListener将会监听spring启动完成事件，
	 	FrameworkServlet.ContextRefreshListener#onApplicationEvent 方法会被调用，
	 	调用调用到DispatcherServlet#initStrategies；
	 7.spring最终在DispatcherServlet#initStrategies中初始化MultipartResolver、LocaleResolver等组件，
	 	所谓的初始化，其实是获取或创建对应的bean，然后赋值给DispatcherServlet的属性。
	 */

	public static void main(String[] args) throws Exception {
//		Tomcat tomcat = new Tomcat();
//		//设置工作目录,tomcat需要使用这个目录进行写一些东西
//		tomcat.setBaseDir("/Users/hjc/IdeaProjects/Spring-Framework/spring-demo/src/main/resources");
//		tomcat.getHost().setAutoDeploy(false);
//
//		Connector connector = new Connector();
//		connector.setPort(9090);
//		connector.setURIEncoding("UTF-8");
//		tomcat.getService().addConnector(connector);
//
//		Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
//		LifecycleListener lifecycleListener = (LifecycleListener)
//				Class.forName(tomcat.getHost().getConfigClass())
//						.getDeclaredConstructor().newInstance();
//		context.addLifecycleListener(lifecycleListener);
//		tomcat.start();
//		tomcat.getServer().await();

		MyWebApplicationContext webApplicationContext = new MyWebApplicationContext();
		webApplicationContext.register(MvcConfig.class);
		webApplicationContext.refresh();

		webApplicationContext.publishEvent(new MyApplicationEvent(Thread.currentThread().getName() + " | 自定义事件 ..."));

		IUserService userService = (IUserService) webApplicationContext.getBean("userService");
		Future<String> login = userService.login();
		System.out.println(login.get());
		System.out.println(userService.getClass());
	}
}
