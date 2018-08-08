package com.test.test;

import com.test.service.DubboTestService;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:06 2018/8/1 0001
 * Modeified By:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(new Date(1533717360000l),"yyyy-MM-dd HH:mm:ss"));
    }
}
