package com.hjc.main;

import config.Appconfig;
import com.hjc.event.MyApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Appconfig.class);
		context.refresh();

		context.publishEvent(new MyApplicationEvent(Thread.currentThread().getName() + " | 自定义事件 ..."));

	}
}
