package com.test.service.impl;

import com.aliyun.oss.OSSClient;
import com.annotation.MyService;
import com.annotation.TestAnnotation;
import com.common.redis.service.RedisService;
import com.common.model.DataInfo;
import com.common.utils.PageUtils;
import com.common.utils.QrCodeUtils;
import com.common.utils.SendMessageUtils;
import com.test.mapper.TestMapper;
import com.test.model.Test;
import com.test.model.TestExample;
import com.test.service.TestService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.security.jca.GetInstance;

import java.io.*;
import java.util.UUID;

import static com.common.utils.WriteExcel.getWorkbok;

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

    private static final String EXCLE_PATH="D:/qrCode.xls";

    private static final  String ACCESS_KEY_ID="ACCESS_KEY_ID";

    private static final String ACCESS_KEY_SECRET="ACCESS_KEY_SECRET";

    private static final String ROLE_ARN="acs:ram::1452872783221063:role/ipasoncn";

    private static final String BUCKET_NAME="psqrcode";

    private static final String ENDPOINT="http://oss-cn-hangzhou.aliyuncs.com";

    private static final  String ROLE_SESSION_NAME="qrCode";

    private static final String REGION="oss-cn-shanghai";



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

    @Override
    public void qrCode() {
        OutputStream out = null;
        try {
            // 获取总列数
            // 读取Excel文档
            File file = new File(EXCLE_PATH);
            Workbook workBook = getWorkbok(file);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            int rowNumber = sheet.getLastRowNum();
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            for (int i=0;i<=rowNumber;i++){
                Row row = sheet.getRow(i);
                Cell urlCell = row.getCell(1);
                String stringCellValue = urlCell.getStringCellValue();
                ByteArrayOutputStream byteArrayOutputStream = QrCodeUtils.encodeImg(stringCellValue);
                String url = upload(byteArrayOutputStream);
                byteArrayOutputStream.close();
                Cell cell = row.createCell(2);
                cell.setCellValue(url);
                System.out.println(stringCellValue+"->"+url);
            }
            out =  new FileOutputStream(EXCLE_PATH);
            workBook.write(out);
            workBook.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String upload(ByteArrayOutputStream outputStream){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 上传文件流。
        String key = UUID.randomUUID().toString();
        key+=".png";
        ossClient.putObject(BUCKET_NAME, key, byteArrayInputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        String url="https://psqrcode.oss-cn-hangzhou.aliyuncs.com/"+key;
        return url;
    }
}
