package com.hjc.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//通过bean工厂拿到业务类InterService的beanDefinition
//		GenericBeanDefinition beanDefinition =
//				(GenericBeanDefinition) beanFactory.getBeanDefinition("interService");
//		System.out.println("扫描注册成功完成后，spring自动调用此方法");
//		System.out.println(beanDefinition.getDescription());

		GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");
		beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_NAME);
	}
}
