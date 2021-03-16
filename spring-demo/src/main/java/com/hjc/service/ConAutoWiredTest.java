package com.hjc.service;

import org.springframework.stereotype.Service;

@Service
public class ConAutoWiredTest {

	//如果只定义了一个合理的构造方法，会自动装配传入，或者自动装配模型为 3 constructor
	public ConAutoWiredTest(InitTest test){
		System.out.println("test:" + test);
	}
}
