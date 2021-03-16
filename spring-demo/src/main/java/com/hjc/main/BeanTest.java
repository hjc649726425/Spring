package com.hjc.main;

import config.Appconfig;
import com.hjc.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class BeanTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册配置类
		context.register(Appconfig.class);
		context.refresh();

		Map<String, User> map = context.getBeansOfType(User.class);
		System.out.println(map);

		System.out.println(context.getBeanDefinition("testUser"));

		User u = (User)context.getBean("testUser");
		System.out.println(u);
	}
}
