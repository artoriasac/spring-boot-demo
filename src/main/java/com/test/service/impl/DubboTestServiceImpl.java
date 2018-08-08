package com.test.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.test.service.DubboTestService;
import org.springframework.stereotype.Component;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 11:31 2018/8/1 0001
 * Modeified By:
 */
@Component
@Service(interfaceClass = DubboTestService.class ,version = "1.0.0")
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return "Hello->"+name;
    }
}
