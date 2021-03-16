package com.hjc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceB {

	@Autowired
	ServiceA a;

	@PostConstruct
	public void init(){
		//这里的c为null
		System.out.println("ServiceB 获取到c:" + a.c);
		System.out.println("a class:" + a.getClass());
		//a.c.Say();
	}

	public ServiceB(){
		System.out.println("ServiceB");
	}
}
