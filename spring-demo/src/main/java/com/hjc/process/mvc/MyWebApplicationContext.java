package com.hjc.process.mvc;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyWebApplicationContext extends AnnotationConfigWebApplicationContext {

	private Tomcat tomcat;

	/**
	 * 重写 postProcessBeanFactory 方法
	 * 在这个方法里添加我们自定义的 MyServletContextAwareProcessor
	 * @param beanFactory
	 */
	@Override
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		beanFactory.addBeanPostProcessor(new MyServletContextAwareProcessor(this));
		beanFactory.ignoreDependencyInterface(ServletContextAware.class);
		WebApplicationContextUtils.registerWebApplicationScopes(getBeanFactory());
	}

	/**
	 * 在这个方法里启动 tomcat
	 */
	@Override
	protected void onRefresh() {
		// 先调用父类的方法
		super.onRefresh();
		// 设置 MyWebApplicationInitializer 的 beanFactory 与 applicationContext
		MyWebApplicationInitializer.setBeanFactory(getBeanFactory());
		MyWebApplicationInitializer.setApplicationContext(this);

		// tomcat的创建及启动
		tomcat = new Tomcat();
		//设置工作目录,tomcat需要使用这个目录进行写一些东西
		tomcat.setBaseDir("/Users/hjc/IdeaProjects/Spring-Framework/spring-demo/src/main/resources");
		tomcat.getHost().setAutoDeploy(false);

		Connector connector = new Connector();
		connector.setPort(9090);
		connector.setURIEncoding("UTF-8");
		tomcat.getService().addConnector(connector);

		Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
		LifecycleListener lifecycleListener = null;
		try {
			lifecycleListener = (LifecycleListener)
					Class.forName(tomcat.getHost().getConfigClass())
							.getDeclaredConstructor().newInstance();
			context.addLifecycleListener(lifecycleListener);
			// 启动tomcat
			//使用spring 容器启动 tomcat 的启动方式时，tomcat 执行DispatcherServlet#init方法时
			tomcat.start();
		} catch (Exception e) {
			System.out.println("启动异常：");
			e.printStackTrace();
		}
	}

}
