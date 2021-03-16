package com.hjc.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InitTest implements InitializingBean, DisposableBean {

	@PostConstruct
	public void init(){

	}

	@PreDestroy
	public void preDestroy(){

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	@Override
	public void destroy() throws Exception {

	}
}
