package com.hjc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationEventListener implements ApplicationListener<MyApplicationEvent> {
	@Override
	public void onApplicationEvent(MyApplicationEvent event) {
		System.out.println(Thread.currentThread().getName() + " | " + event.getSource());
	}
}
