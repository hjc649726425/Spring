package com.hjc.life;

import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class LifeInit implements SmartLifecycle {

	@Override
	public void start() {
		System.out.println("start");
	}

	@Override
	public void stop() {

	}

	@Override
	public boolean isRunning() {
		return false;
	}

	//自动调用
	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		callback.run();
	}

	//优先级
	@Override
	public int getPhase() {
		return 0;
	}
}
