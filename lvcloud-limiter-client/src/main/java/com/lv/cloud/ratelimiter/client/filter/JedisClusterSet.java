package com.lv.cloud.ratelimiter.client.filter;

import redis.clients.jedis.JedisCluster;


public interface JedisClusterSet {
	
	JedisCluster getJedisCluster();
}
