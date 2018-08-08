package com.test.service.impl;

import com.annotation.TestAnnotation;
import com.common.redis.service.RedisService;
import com.common.model.DataInfo;
import com.common.utils.PageUtils;
import com.common.utils.SendMessageUtils;
import com.test.mapper.TestMapper;
import com.test.model.Test;
import com.test.model.TestExample;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:05 2018/7/30 0030
 * Modeified By:
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private RedisService redisService;


    @Value("${ACCESS_KEY_ID}")
    private String ACCESS_KEY_ID;

    @Value("${server.port}")
    private String port;

    @TestAnnotation("3")
    @Override
    public DataInfo<Test> test() {
        PageUtils.startPage(1,3);
        //SendMessageUtils.sendMessage("18502742415","123456");
        System.out.println(port);
        return PageUtils.getDataInfo(testMapper.selectByExample(new TestExample()));
    }
}
