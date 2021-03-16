package com.hjc.config;

import com.hjc.anno.ConditionalForClass;
import com.hjc.condition.LinuxCondition;
import com.hjc.condition.WindowsCondition;
import com.hjc.model.User;
import com.hjc.service.ServiceD1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

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

	@Bean("taskExecutor12345")
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10); //核心线程数
		executor.setMaxPoolSize(20);  //最大线程数
		executor.setQueueCapacity(1000); //队列大小
		executor.setKeepAliveSeconds(300); //线程最大空闲时间
		executor.setThreadNamePrefix("fsx-Executor-"); //指定用于新创建的线程名称的前缀。
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略（一共四种，此处省略）
		executor.initialize();
		return executor;
	}
}
