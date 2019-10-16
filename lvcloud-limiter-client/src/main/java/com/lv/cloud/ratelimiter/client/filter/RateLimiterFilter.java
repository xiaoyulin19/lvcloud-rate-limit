package com.lv.cloud.ratelimiter.client.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.lv.cloud.framework.config.SpringContextHolder;
import com.lv.cloud.jedis.JedisClusterAdapter;
import com.lv.cloud.ratelimiter.client.config.RATE_LIMTER_MODE;
import com.lv.cloud.ratelimiter.context.RateLimiterContextBean;
import com.lv.cloud.ratelimiter.exception.RateLimiterException;
import com.lv.cloud.ratelimiter.service.JedisClusterRateLimiter;
import com.lv.cloud.ratelimiter.service.LocalRateLimiter;
import redis.clients.jedis.JedisCluster;


/**
 * 本地限流（非分布式）<br/>
 * 按渠道限流得自己实现 <br/>
 * 调取LocalRateLimiter.isLimited(String method, String distributorChannel)，判断是否被限流
 * @author xiaoyulin
 *
 */
@Activate(group = Constants.CONSUMER, order=100)
public class RateLimiterFilter implements Filter {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterFilter.class);

	private static JedisCluster jedisCluster;

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String method = invoker.getInterface().getName()+"/"+invocation.getMethodName();
		String distributorChannel = null; // 渠道
		
		if((RATE_LIMTER_MODE.LOCAL.getMode() == RateLimiterContextBean.MODE
			||RATE_LIMTER_MODE.ALL.getMode() == RateLimiterContextBean.MODE) &&
				LocalRateLimiter.isLimited(method, distributorChannel)){
			return new RpcResult(new RateLimiterException("The method's connection is over limit!"));
		}

		if((RATE_LIMTER_MODE.DISTRIBUTED.getMode() == RateLimiterContextBean.MODE
				||RATE_LIMTER_MODE.ALL.getMode() == RateLimiterContextBean.MODE) &&
				JedisClusterRateLimiter.getInstance(getJedisCluster()).isLimited(method, distributorChannel)){
			return new RpcResult(new RateLimiterException("The method's connection is over limit!(redis)"));
		}
		
		return invoker.invoke(invocation);  
	}

	public JedisCluster getJedisCluster() {
		if (jedisCluster == null){
			JedisClusterAdapter jedisClusterAdapter = (JedisClusterAdapter) SpringContextHolder.getBean(JedisClusterAdapter.class);
			jedisCluster = jedisClusterAdapter.getJedisCluster();
		}
		return jedisCluster;
	}

}
