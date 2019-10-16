package com.lv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-15
 */
@SpringBootApplication
public class RatelimiterServer {

    public static void main(String[] args) {
        SpringApplication.run(RatelimiterServer.class,args);
    }
}
