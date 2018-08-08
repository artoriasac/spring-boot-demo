package com.artorias.demo;

import com.common.model.DataInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.model.Test;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:29 2018/7/30 0030
 * Modeified By:
 */
@RestController
public class HelloWorld {

    @Autowired
    private TestService testService;

    @PostMapping("helloWorld")
    public String helloWorld(){
        DataInfo<Test> test = testService.test();
        return "helloWorld";
    }
}
