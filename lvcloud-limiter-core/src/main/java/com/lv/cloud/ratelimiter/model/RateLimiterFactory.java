package com.lv.cloud.ratelimiter.model;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 限流策略工厂
 * @author xiaoyulin
 *
 */
public class RateLimiterFactory {
	
	public final static String default_distributor_channel = "-1000";
	
	/**
	 * guava单机令牌桶限流
	 */
	private final static Map<String, Map<String, RateLimiter>> rateLimiterMap = new HashMap<String, Map<String, RateLimiter>>();

	public static Map<String, Map<String, RateLimiter>> getRatelimitermap() {
		return rateLimiterMap;
	}
	
	/**
	 * 非分布式限流
	 * @param method
	 * @param permitsPerSecond 每秒允许次数
	 */
	public static void addRatelimiter(String method, Long permitsPerSecond, String distributorChannel){
		Map<String, RateLimiter> map = rateLimiterMap.get(method);
		if(map == null){
			map = new HashMap<String, RateLimiter>();
			rateLimiterMap.put(method, map);
		}
		if(StringUtils.isBlank(distributorChannel)){
			map.put(default_distributor_channel, RateLimiter.create(permitsPerSecond));
		}else {
			map.put(distributorChannel, RateLimiter.create(permitsPerSecond));
		}
	}
	
	/**
	 * 移除某方法所有限流设置（非分布式）
	 * @param method
	 */
	public static void removeRateLimiter(String method){
		rateLimiterMap.remove(method);
	}
	
	/**
	 * 移除某方法的相应渠道限流设置（非分布式）
	 * @param method
	 * @param distributorChannel
	 */
	public static void removeRateLimiter(String method, String distributorChannel){
		Map<String, RateLimiter> map = rateLimiterMap.get(method);
		if(map != null){
			map.remove(distributorChannel);
		}
	}
	
	/**
	 * 获取限流对象
	 * @param method
	 * @param distributorChannel
	 * @return
	 */
	public static RateLimiter getRateLimiter(String method, String distributorChannel){
		if(rateLimiterMap == null || rateLimiterMap.isEmpty()){
			return null;
		}
		RateLimiter rateLimiter = null;
		Map<String, RateLimiter> map = rateLimiterMap.get(method);
		if(map != null){
			if(StringUtils.isNotBlank(distributorChannel)){
				rateLimiter = map.get(distributorChannel);
			}
			if(rateLimiter == null){
				rateLimiter = map.get(default_distributor_channel);
			}
			
		}
		
		return rateLimiter;
	}
	
	/**
	 * 清理限流数据
	 */
	public static void clearData(){
		RateLimiterFactory.getRatelimitermap().clear();
		
	}
  
}
