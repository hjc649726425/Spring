package com.hjc.process.mvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Component
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	private static BeanFactory beanFactory;

	private static AbstractRefreshableWebApplicationContext applicationContext;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// 从 beanFactory 中获取 DispatcherServlet 并注册到servlet容器
		DispatcherServlet servlet = beanFactory.getBean(DispatcherServlet.class);
		// loadOnStartup 设置成 -1 时，只有在第一次请求时，才会调用 init 方法
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");

		// 为 applicationContext 设置 servletContext
		applicationContext.setServletContext(servletContext);
	}

	/**
	 * 设置 beanFactory
	 * 为什么要设置 beanFactory的值？因为 DispatcherServlet 要从 beanFactory 中获取
	 * @param beanFactory
	 * @throws BeansException
	 */
	public static void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		MyWebApplicationInitializer.beanFactory = beanFactory;
	}

	/**
	 * 设置 applicationContext
	 * 为什么要设置 applicationContext 的值？因为 servletContext 要设置到 applicationContext
	 * @param applicationContext
	 */
	public static void setApplicationContext(
			AbstractRefreshableWebApplicationContext applicationContext) {
		MyWebApplicationInitializer.applicationContext = applicationContext;
	}
}
