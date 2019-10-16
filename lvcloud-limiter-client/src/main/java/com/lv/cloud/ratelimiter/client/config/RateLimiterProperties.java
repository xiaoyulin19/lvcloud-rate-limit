package com.lv.cloud.ratelimiter.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoyulin
 * @description 限流配置
 * @date 2019-10-16
 */
@ConfigurationProperties(
        prefix = "lvcloud.rate.limiter"
)
public class RateLimiterProperties {

    /**
     * 限流模式（0：只支持本地限流（默认），1：只支持分布式限流，2：支持本地&分布式限流）
     */
    private int mode = 0;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
