package com.hjc.process;

import com.hjc.model.User;
import com.hjc.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryAware implements BeanFactoryAware {
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryAware");
		//System.out.println(beanFactory.getBean(UserService.class));
		//System.out.println(beanFactory.getBean("userService"));
	}
}
