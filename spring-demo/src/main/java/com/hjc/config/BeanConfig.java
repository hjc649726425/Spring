package com.hjc.config;

import com.hjc.anno.ConditionalForClass;
import com.hjc.condition.LinuxCondition;
import com.hjc.condition.WindowsCondition;
import com.hjc.model.User;
import com.hjc.service.ServiceD1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Conditional({WindowsCondition.class})
	@Bean(name = "bill")
	public User user1(){
		return new User("Bill Gates",62);
	}

	@Conditional({LinuxCondition.class})
	@Bean("linus")
	public User user2(){
		return new User("Linus",48);
	}

	@Bean("d1")
	@ConditionalForClass(className = "com.hjc.service.ServiceD")
	public ServiceD1 d1(){
		return new ServiceD1();
	}
}
