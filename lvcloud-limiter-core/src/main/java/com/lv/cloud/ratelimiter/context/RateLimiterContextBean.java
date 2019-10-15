package com.lv.cloud.ratelimiter.context;


import com.lv.cloud.ratelimiter.model.RateLimiterFactory;
import com.lv.cloud.ratelimiter.utils.XmlParseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 初始化需要限流方法，及相应限流策略进应用上下文
 * @author xiaoyulin
 *
 */
public class RateLimiterContextBean implements InitializingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterContextBean.class);
	
	/**
	 * 需要限流的方法集合
	 */
	private final static String RATE_LIMITER_FILE_NAME = "lvcloud-rate-limiter.xml";
	
	public void resetRateLimiterContext(){
		try {
			RateLimiterMethods rateLimiterMethods = XmlParseUtils.parse(RATE_LIMITER_FILE_NAME, RateLimiterMethods.class);
			
			RateLimiterFactory.clearData();
			
			if(rateLimiterMethods != null && rateLimiterMethods.getRateLimiterMethodList() != null 
					&& rateLimiterMethods.getRateLimiterMethodList().size() > 0){
				List<RateLimiterMethod> methodList = rateLimiterMethods.getRateLimiterMethodList();
				for(RateLimiterMethod method : methodList){
					if(RATE_LIMITER_TYPE.distributed_rate_limiter.getTypeCode() == method.getType()){
//						String timeUnit = method.getTimeunit();
//						if(StringUtils.isBlank(timeUnit)){
//							RateLimiterFactory.addDistributedRateLimiter(method.getName(), method.getPermits(), null, method.getDistributorChannel());
//						}else if(TimeUnit.SECONDS.name().equals(timeUnit) || TimeUnit.MINUTES.name().equals(timeUnit)){
//							RateLimiterFactory.addDistributedRateLimiter(method.getName(), method.getPermits(), method.getTimeunit(), method.getDistributorChannel());
//						}
					}else if(RATE_LIMITER_TYPE.rate_limiter.getTypeCode() == method.getType()){
						RateLimiterFactory.addRatelimiter(method.getName(), method.getPermits(), method.getDistributorChannel());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("RateLimiterContextBean init error,{}", e);
		}
		
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		resetRateLimiterContext();
	}

	public enum RATE_LIMITER_TYPE {
		rate_limiter(1, "单机限流"),
		distributed_rate_limiter(2, "分布式限流");
		
		private int typeCode;
		private String declaration;

		public int getTypeCode() {
			return typeCode;
		}

		public void setTypeCode(int typeCode) {
			this.typeCode = typeCode;
		}

		public String getDeclaration() {
			return declaration;
		}

		public void setDeclaration(String declaration) {
			this.declaration = declaration;
		}

		RATE_LIMITER_TYPE(int typeCode, String declaration) {
			this.typeCode = typeCode;
			this.declaration = declaration;
		}
	}
}
