package com.lv.test;


import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
public class LvcloudTest {

    @Reference
    private ITestService testService;

    @Test
    public void test(){
        for(int i = 0;i<10;i++) {
            System.out.println("###test:" + testService.test());
        }
    }
}
