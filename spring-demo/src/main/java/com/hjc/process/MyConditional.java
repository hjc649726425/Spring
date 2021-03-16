package com.hjc.process;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyConditional implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		//从这里获取配置文件中 active 的值，根据当前的active值决定是否加载类
		String[] activeProfiles = environment.getActiveProfiles();
		for (String active : activeProfiles) {
			/*if(active.equals(ActiveEnum.open_active.getActive().toString())){
				return true;
			}*/
			System.out.println(active);

		}
		return true;
	}
}
