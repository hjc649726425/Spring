package com.hjc.main;

import config.Appconfig;
import com.hjc.process.MyNameGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;

public class MyGeneratorTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Appconfig.class);
		//创建一个名字生成器类
		MyNameGenerator myNameGenerator = new MyNameGenerator();
//通过上面的源码可知，必须以单例的方式注册的bean工厂中
		context.getBeanFactory().registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR,myNameGenerator);
		context.refresh();
		System.out.println();
	}
}
