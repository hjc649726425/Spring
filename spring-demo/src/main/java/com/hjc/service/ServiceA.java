package com.hjc.service;

import com.hjc.anno.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceA {

	@Autowired
	ServiceB b;

	@Autowired
	ServiceC c;

	public ServiceA(){
		System.out.println("ServiceA");
	}

	//@Test("test value")
	public void test(){
		System.out.println("执行test");
	}
}
