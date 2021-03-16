package com.hjc.service;

import com.hjc.dao.UserMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
//@Scope("prototype")
public class UserService implements IUserService, InitializingBean, DisposableBean {


	public void setTestService(ServiceTest testService) {
		this.testService = testService;
	}

	@Autowired(required = true)
	@Qualifier("serviceTest")
	ServiceTest testService;

	public UserService(ServiceA a, ServiceB b){
		System.out.println("ababababababab");
	}


	public UserService(){
		System.out.println("ooooooooooooo");
	}


	public UserService(ServiceA a){
		System.out.println("aaaaaaaaaa");
	}

	//@Autowired(required = true)
	public UserService(ServiceB b){
		System.out.println(b);
		System.out.println("bbbbbbbbbbbb");
	}

	@PostConstruct
	public void init(){
		System.out.println(testService.value);
		System.out.println("开始 login");
		login();
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	//@Autowired(required = true)
	@Resource
	UserMapper userMapper;

	public void add(){
		userMapper.add();
		System.out.println("add");
	}

	public void asd(){
		System.out.println(12345);
	}

	@Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	@Override
	public String login() {
		System.out.println("login test:" + testService.value);
		return "login";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("执行DisposableBean#destroy 方法");
	}
}
