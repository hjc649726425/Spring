package com.hjc.process;

import com.hjc.service.ServiceC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ServiceC){
			ServiceC c = (ServiceC) bean;
			System.out.println("初始化前调用 PostProcessorBeforeInitialiation");
			c.Say();
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ServiceC){
			ServiceC c = (ServiceC) bean;
			System.out.println("初始化后调用 postProcessorAfterInitialiation");
			c.Say();
		}
		return bean;

	}
}
