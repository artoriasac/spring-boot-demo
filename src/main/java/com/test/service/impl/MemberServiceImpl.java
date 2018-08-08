package com.test.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.artorias.memberService.MemberService;
import org.springframework.stereotype.Component;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 11:23 2018/8/2 0002
 * Modeified By:
 */
@Component
@Service(interfaceClass = MemberService.class ,version = "1.0.0")
public class MemberServiceImpl implements MemberService {
    @Override
    public void sayHello(String s) {
        System.out.println("hello->"+s);
    }
}
