package com.lv.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@SpringBootApplication
@ServletComponentScan
public class RateLimiterTestApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RateLimiterTestApp.class, args);
    }
}
