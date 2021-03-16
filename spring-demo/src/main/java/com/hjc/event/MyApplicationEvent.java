package com.hjc.event;

import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1L;

	public MyApplicationEvent(Object source) {
		super(source);
	}
}
