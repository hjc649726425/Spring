package com.hjc.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class Test {

	public void dosm(){
		System.out.println("做一些事情...");
	}
}
