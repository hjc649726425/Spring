package com.hjc.aspect;

import com.hjc.anno.Test;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

	@Pointcut("execution(* com.hjc.service.UserService.*(..))")
	private void test(){
	}

	@Pointcut("@annotation(com.hjc.anno.Test)")
	private void test2(){
	}

	@Pointcut("execution(* com.hjc.service.ServiceA.*(..))")
	private void test3(){
	}

	@Around("test()")
	public Object round(ProceedingJoinPoint point){
		Object ret = null;
		try {
			System.out.println("before");
			ret = point.proceed();
			System.out.println("after");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		return ret;
	}

	@Around("test2()")
	public void round2(ProceedingJoinPoint point){
		try {
			System.out.println("before2");
			Class<?> c = point.getTarget().getClass();
			String methodName = point.getSignature().getName();
			if(c.getMethod(methodName).isAnnotationPresent(Test.class)){
				Test t = c.getMethod(methodName).getAnnotation(Test.class);
				System.out.println(t.value());
			}
			point.proceed();
			System.out.println("after2");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	@Before("test3()")
	public void before() {
		System.out.println("before......");
	}
}
