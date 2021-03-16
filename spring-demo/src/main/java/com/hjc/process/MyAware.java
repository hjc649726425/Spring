package com.hjc.process;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

//invokeAwareMethods中会调动Aware接口对应的方法
@Component
public class MyAware implements BeanNameAware {
	@Override
	public void setBeanName(String name) {

	}
}
