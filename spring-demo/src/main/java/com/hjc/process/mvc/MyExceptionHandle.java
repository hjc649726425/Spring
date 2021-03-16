package com.hjc.process.mvc;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
public class MyExceptionHandle {

	@ExceptionHandler(Exception.class)
	public String handler(HandlerMethod handlerMethod, Exception exception) {
		// 一些处理操作
		System.out.println(exception);
		System.out.println(handlerMethod);
		return "error";
	}
}