package com.test.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpMultipart {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        String startString = "D:/download/data/xld/";
        String fileExt=".xls";
        String fileStart="JB";
        for (int i=212;i<=221;i++){
            Integer integer=new Integer(i);
            String s = integer.toString();
            while (s.length()<3){
                s="0"+s;
            }
            String fileName=fileStart+s+fileExt;
            String fileUrl=startString+fileName;
            String sURL = "http://localhost:8080/faultInfo/uploadExcel";

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost uploadFile = new HttpPost(sURL);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //builder.addTextBody("field1", "yes", ContentType.APPLICATION_JSON);
            // 把文件加到HTTP的post请求中
            File f = new File(fileUrl);
            builder.addBinaryBody(
                    "faultInfoExcel",
                    new FileInputStream(f),
                    ContentType.APPLICATION_JSON,
                    fileName
            );
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
            System.err.println(fileName+"Post 返回结果" + sResponse);
        }

    }
}
