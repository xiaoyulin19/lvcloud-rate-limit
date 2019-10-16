package com.lv.cloud.ratelimiter.client.filter;

import com.alibaba.dubbo.rpc.*;
import com.lv.cloud.ratelimiter.exception.RateLimiterException;
import com.lv.cloud.ratelimiter.service.JedisClusterRateLimiter;

/**
 * 分布式限流 <br/>
 * 按渠道限流得自己实现 <br/>
 * 调取JedisClusterRateLimiter.isLimited(String method, String distributorChannel)，判断是否被限流

 * @author xiaoyulin
 *
 */
//@Activate(group = Constants.CONSUMER, order=100)
public abstract class AbstractJedisClusterRateLimiterFilter implements Filter, JedisClusterSet {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJedisClusterRateLimiterFilter.class);
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String method = invoker.getInterface().getName()+"/"+invocation.getMethodName();
		String distributorChannel = null; // 渠道
		
		JedisClusterRateLimiter jedisClusterRateLimiter = JedisClusterRateLimiter.getInstance(getJedisCluster());
		if(jedisClusterRateLimiter.isLimited(method, distributorChannel)){
			return new RpcResult(new RateLimiterException("The method's connection is over limit!（redis）"));
		}
		
		return invoker.invoke(invocation);  
	}

}
