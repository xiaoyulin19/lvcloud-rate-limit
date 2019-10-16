package com.lv.test;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-15
 */
@Service(interfaceClass = ITestService.class, timeout=60000)
@Component("testService")
public class TestServiceImpl implements ITestService {

    @Override
    public String test(String id) {
        return "test:" + id;
    }
}
