package com.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class SendHttpRequestUtils {
    private static RestTemplate restTemplate=new RestTemplate();

    public static void SendFileRequest(String url,File file){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost uploadFile = new HttpPost(url);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "multipartFile",
                    file,
                    ContentType.APPLICATION_JSON,
                    file.getName()
            );
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
            System.err.println(file.getName()+"----"+file.getPath()+"Post 返回结果" + sResponse);
        }catch (Exception e){

        }
    }

    public static ResponseEntity<T>  SendGetRequest(String url, Class T){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<T> resp = restTemplate.exchange(url, HttpMethod.GET, null,T);
        return resp;
    }
}
