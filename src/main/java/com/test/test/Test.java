package com.test.test;

import com.algorithm.dynamiclink.DynamicLink;
import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.lang3.time.DateFormatUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:06 2018/8/1 0001
 * Modeified By:
 */
public class Test {
    public static void main(String[] args) throws Exception{
        File file=new File("D:\\barcode.png");
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes=new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        BASE64Encoder base64Encoder=new BASE64Encoder();
        System.out.println(base64Encoder.encode(bytes));
    }
}
