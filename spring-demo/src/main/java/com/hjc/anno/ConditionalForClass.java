package com.hjc.anno;

import com.hjc.condition.ConditionTest;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 组合了 @Conditional 的功能，处理条件匹配的类为 ConditionTest
@Conditional(ConditionTest.class)
public @interface ConditionalForClass {
	String className() default "";
}
