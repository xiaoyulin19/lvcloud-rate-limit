package com.lv.cloud.ratelimiter.client.config;

import com.lv.cloud.framework.annotation.EnableSpringContextHolder;
import com.lv.cloud.jedis.annotation.EnableJedisCluster;
import com.lv.cloud.ratelimiter.context.RateLimiterContextBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-15
 */
@Configuration
@EnableConfigurationProperties(RateLimiterProperties.class)
@EnableJedisCluster
@EnableSpringContextHolder
public class RateLimiterConfig {

    @Bean
    @ConditionalOnMissingBean
    public RateLimiterContextBean rateLimiterContextBean(RateLimiterProperties rateLimiterProperties){
        return new RateLimiterContextBean(rateLimiterProperties.getMode());
    }


}
