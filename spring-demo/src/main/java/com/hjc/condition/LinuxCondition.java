package com.hjc.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class LinuxCondition  implements Condition {
	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

		Environment environment = conditionContext.getEnvironment();

		String property = environment.getProperty("os.name");
		if (property.contains("Linux") || property.contains("Mac")){
			return true;
		}
		return false;
	}
}