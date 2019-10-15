package com.lv.cloud.ratelimiter.service;

import com.google.common.util.concurrent.RateLimiter;
import com.lv.cloud.ratelimiter.model.RateLimiterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 只支持秒级
 * @author xiaoyulin
 *
 */
public class LocalRateLimiter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalRateLimiter.class);
	
	public static boolean isLimited(String method, String distributorChannel) {
		RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(method, distributorChannel);
		if(rateLimiter != null){
			LOGGER.info("LocalRateLimiter.isLimited true,method=" + method + ",distributorChannel=" + distributorChannel);
			return !rateLimiter.tryAcquire();			
		}
		
		return false;
	}
	
}
