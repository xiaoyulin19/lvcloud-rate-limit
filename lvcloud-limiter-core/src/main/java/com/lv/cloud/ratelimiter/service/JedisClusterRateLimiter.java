package com.lv.cloud.ratelimiter.service;


import com.lv.cloud.ratelimiter.model.DistributedRateLimiter;
import com.lv.cloud.ratelimiter.model.RateLimiterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;

public class JedisClusterRateLimiter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisClusterRateLimiter.class);
	
	private static Object LOCK = new Object();
	
	private static JedisClusterRateLimiter instance;
	
	private JedisCluster jedisCluster;
	
	private JedisClusterRateLimiter(){
		init();
	}
	
	private JedisClusterRateLimiter(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	private void init() {
		// TODO
	}

	/**
	 * 静态实始化
	 * @return
	 */
	public static JedisClusterRateLimiter getInstance(JedisCluster jedisCluster) {
		if(instance == null){
			synchronized(LOCK) {
				if (instance==null) {
					instance = new JedisClusterRateLimiter(jedisCluster);
				}
			}
		}
		return instance;
	}
	
	
	public boolean isLimited(String method, String distributorChannel) {
		DistributedRateLimiter distributedRateLimiter = RateLimiterFactory.getDistributedRateLimiter(method, distributorChannel);
		if(distributedRateLimiter != null){
			LOGGER.info("JedisClusterRateLimiter.isLimited true,method=" + method + ",distributorChannel=" + distributorChannel);
			Long rateLimiterNum = distributedRateLimiter.getPermits();
			String timeUnit = distributedRateLimiter.getTimeUnit();
			
			Long count = this.jedisCluster.incr(method);
			if (count == 1) {
				//设置有效期1s
				if(TimeUnit.SECONDS.name().equals(timeUnit)){
					this.jedisCluster.expire(method, 1);
				}else if(TimeUnit.MINUTES.name().equals(timeUnit)){
					this.jedisCluster.expire(method, 60);
				}
			}
			
			if(count > rateLimiterNum 
					&& (TimeUnit.SECONDS.name().equals(timeUnit) || TimeUnit.MINUTES.name().equals(timeUnit))){
				return true;
			}
		}
		
		return false;
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	
}
