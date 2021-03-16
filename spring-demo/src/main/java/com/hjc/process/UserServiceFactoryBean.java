package com.hjc.process;

import com.hjc.model.User;
import com.hjc.service.ServiceTest;
import com.hjc.service.UserService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//一些bean实例化比较复杂
//用加& 获取该实例bean
//不加& ,返回的是getObject 返回的对象
// 每次获取bean都会调用getObject 方法获取
@Component("userService2")
public class UserServiceFactoryBean implements FactoryBean<UserService> {
	@Override
	public UserService getObject() throws Exception {
		UserService userService = new UserService();
		userService.setTestService(new ServiceTest("1234"));
		return userService;
	}

	@Override
	public Class<?> getObjectType() {
		return UserService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
