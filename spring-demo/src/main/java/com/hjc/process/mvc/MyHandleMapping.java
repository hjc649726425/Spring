package com.hjc.process.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyHandleMapping implements HandlerMapping {
	@Override
	public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
		return null;
	}
}
