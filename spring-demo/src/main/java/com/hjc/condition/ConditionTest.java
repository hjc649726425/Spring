package com.hjc.condition;

import com.hjc.anno.ConditionalForClass;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class ConditionTest implements Condition {
	//只有返回true的时候，ConfigurationClassPostProcessor才会将其对应的 bean
	// 注册到 beanFactory 的 beanDefinitionMap 中
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 获取 @ConditionalForClass 注解的所有属性值
		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
				ConditionalForClass.class.getName());
		// 获取className的属性值，就是 @ConditionalForClass 的 className 属性
		String className = (String)annotationAttributes.get("className");
		//Object userService = context.getBeanFactory().getBean("userService");
		if(null == className || className.length() <= 0) {
			return true;
		}
		try {
			// 判断类是否存在
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}
