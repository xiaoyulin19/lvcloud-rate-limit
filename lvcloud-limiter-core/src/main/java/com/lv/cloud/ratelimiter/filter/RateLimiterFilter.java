package com.lv.cloud.ratelimiter.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.lv.cloud.ratelimiter.exception.RateLimiterException;
import com.lv.cloud.ratelimiter.service.LocalRateLimiter;


/**
 * 本地限流（非分布式）<br/>
 * 按渠道限流得自己实现 <br/>
 * 调取LocalRateLimiter.isLimited(String method, String distributorChannel)，判断是否被限流
 * @author xiaoyulin
 *
 */
@Activate(group = Constants.CONSUMER, order=101)
public class RateLimiterFilter implements Filter {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterFilter.class);
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String method = invoker.getInterface().getName()+"/"+invocation.getMethodName();
		String distributorChannel = null; // 渠道
		
		if(LocalRateLimiter.isLimited(method, distributorChannel)){
			return new RpcResult(new RateLimiterException("The method's connection is over limit!"));
		}
		
		return invoker.invoke(invocation);  
	}

}
