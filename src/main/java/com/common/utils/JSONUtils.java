package com.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:13 2018/7/30 0030
 * Modeified By:
 */
public class JSONUtils {
    private final static ObjectMapper mapper = new ObjectMapper();

    private JSONUtils() {

    }

    public static ObjectMapper getInstance() {
        return mapper;
    }

    public static  String  objToJson(Object object){
        String json = null;
        try {
            //将java对象转换成json字符串
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static  <T>  T jsonToObj(String json,Class<T> type){
        T obj = null;
        try {
            obj = mapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String mapToJson(Map json) {
        ObjectMapper mapper = new ObjectMapper();
        String map = null;
        try {
            map = mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static  <T> List<T> jsonToList(String json, Class<T> type) {
        List<T> list=null;
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,type);
        try {
            //再将这个type作为转换的目标type
            list = mapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String,Object> jsonToMap(String json) {
        Map<String, Object> map = null;
        //和List一样，Map依旧需要反序列化
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
        try {
            //JSONObject jsonObject = new JSONObject(messageMap);
            map = mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
