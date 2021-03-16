package com.hjc.service;

import org.springframework.stereotype.Service;

//@Service
public class ServiceTest {
	public String value;

	public ServiceTest(String value){
		this.value = value;
	}

    private void init() {
		System.out.println("bean init");
    }
}
