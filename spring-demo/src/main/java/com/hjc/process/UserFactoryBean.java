package com.hjc.process;

import com.hjc.model.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//一些bean实例化比较复杂
//用加& 获取该实例bean
//不加& ,返回的是getObject 返回的对象
// 每次获取bean都会调用getObject 方法获取
@Component("userFactory")
public class UserFactoryBean implements FactoryBean<User> {
	Integer age = 10;

	@Override
	public User getObject() throws Exception {
		User u = new User();
		u.setAge(++age);
		u.setName("张三");
		return u;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
