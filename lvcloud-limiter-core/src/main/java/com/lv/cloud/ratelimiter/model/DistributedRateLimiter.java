package com.lv.cloud.ratelimiter.model;

import java.util.concurrent.TimeUnit;

/**
 * 分布式接口限流模型
 * @author xiaoyulin
 *
 */
public class DistributedRateLimiter {
	
	/**
	  * 限流的方法
	  */
	private String method;
	 /**
	  * 限流次数
	  */
	private Long permits;
	 /**
	  * 限流级别（秒级(SECONDS)，分钟(MINUTES)）
	  */
	private String timeUnit;
	
	public DistributedRateLimiter(){}
	
	public DistributedRateLimiter(String method, Long num){
		this(method, num, TimeUnit.SECONDS.name());
	}
	
	/**
	 * 
	 * @param method
	 * @param permits
	 * @param timeUnit 默认秒级
	 */
	public DistributedRateLimiter(String method, Long permits, String timeUnit){
		this.method = method;
		this.permits = permits;
		this.timeUnit = timeUnit;
		
		if(this.timeUnit == null){
			this.timeUnit = TimeUnit.SECONDS.name();
		}
	}
	 
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Long getPermits() {
		return permits;
	}

	public void setPermits(Long permits) {
		this.permits = permits;
	}
}
