package com.lv.cloud.ratelimiter.client.config;

import com.lv.cloud.ratelimiter.context.RateLimiterContextBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-15
 */
@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiterContextBean rateLimiterContextBean(){
        return new RateLimiterContextBean();
    }
}
