package com.test.controller;

import com.annotation.Login;
import com.common.rabbitMq.impl.RabbitMqServiceImpl;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.common.utils.QrCodeUtils;
import com.test.model.Test;
import com.test.service.TestService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:04 2018/7/30 0030
 * Modeified By:
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private RabbitMqServiceImpl rabbitMqService;


    @PostMapping("test")
    public Result<DataInfo<Test>> test(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("artorias")==null){
            session.setAttribute("artorias","abyss");
        }else {
            System.out.println(session.getAttribute("artorias"));
        }
        return new Result<>(testService.test());
    }

    @PostMapping("qrCode")
    public void qrCode(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("1","2");
        try {
            QrCodeUtils.getQrcode("artorias.club/test/test",response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QrCodeUtils.analysisQrcode();

    }

    @ResponseBody
    @RequestMapping("cookie/{browser}")
    public String cookie(@PathVariable("browser") String browser, HttpServletRequest request, HttpSession session) {
        //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return "index";
    }

}
