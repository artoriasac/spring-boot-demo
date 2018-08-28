package com.test.test;

import com.aliyun.oss.OSSClient;
import com.common.utils.QrCodeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:20 2018/8/27 0027
 * Modeified By:
 */
public class UploadTest {

    public static void main(String[] args)throws Exception {

        ByteArrayOutputStream byteArrayOutputStream = QrCodeUtils.encodeImg("http://api.jwxba.com/g.php?encrypt=9f940775571bb9b075bbab7cb5244fd5&t=4");

    }
}
