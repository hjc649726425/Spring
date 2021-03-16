package com.hjc.process.mvc;

import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.context.support.ServletContextAwareProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class MyServletContextAwareProcessor extends ServletContextAwareProcessor {

	AbstractRefreshableWebApplicationContext webApplicationContext;

	/**
	 * 传入 webApplicationContext
	 * @param webApplicationContext
	 */
	public MyServletContextAwareProcessor(
			AbstractRefreshableWebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}

	/**
	 * 返回 ServletContext
	 * 先从 webApplicationContext 中获取，如果获取不到，再从父类方法中获取
	 * @return
	 */
	@Override
	protected ServletContext getServletContext() {
		ServletContext servletContext = this.webApplicationContext.getServletContext();
		return (servletContext != null) ? servletContext : super.getServletContext();
	}

	//获取servletContext时，先从webApplicationContext中获取，
	// 如果获取不到，再从父类方法中获取。
	@Override
	protected ServletConfig getServletConfig() {
		ServletConfig servletConfig = this.webApplicationContext.getServletConfig();
		return (servletConfig != null) ? servletConfig : super.getServletConfig();
	}
}
