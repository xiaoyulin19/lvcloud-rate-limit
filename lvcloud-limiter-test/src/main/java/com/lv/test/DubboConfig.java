package com.lv.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.spring.boot.DubboProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author houjian
 * @date 2019/8/26.
 */
@Configuration
public class DubboConfig {
    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public ApplicationConfig applicationConfig() {
        return dubboProperties.getApplication();
    }

    @Bean
    public RegistryConfig registryConfig() {
        return dubboProperties.getRegistry();
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        return dubboProperties.getProtocol();
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        return dubboProperties.getConsumer();
    }
}
