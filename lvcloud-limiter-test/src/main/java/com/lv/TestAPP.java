package com.lv;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.lv.cloud.ratelimiter.client.config.EnableRateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-15
 */
@SpringBootApplication
@EnableDubbo
//@DubboComponentScan({"com.lv.test"})
@EnableRateLimiter
public class TestAPP {

    public static void main(String[] args) {
        SpringApplication.run(TestAPP.class,args);
    }
}
