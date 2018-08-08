package com.common.redis.service.impl;

import com.common.redis.service.RedisService;
import com.common.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:03 2018/7/30
 * Modeified By:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String ZERO="0";

    private Long ZERO_TIME=0l;


    public void  set(String key,String value){
        if (key==null||value==null){
            return;
        }
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     *@Author : artorias
     *@Description :
     *@Date : 2018/7/30 0030
     *@param key
     *@param value
     *@param seconds
     */
    public void  set(String key,String value,Long seconds){
        if (key==null||value==null){
            return;
        }
        if (seconds==null){
            set(key,value);
        }
        else {
            redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);

        }
    }

    public String get(String key){
        if (key==null){
            return null;
        }
       return redisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key,Class<T> t){
        if (key==null||t==null){
            return null;
        }
        String value = get(key);
        T result=null;
        try {
            result=JSONUtils.jsonToObj(value,t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> List<T> getList(String key,Class<T> type){
        if (key==null||type==null){
            return null;
        }
        String value = get(key);
        List<T> result=null;
        try {
            result=JSONUtils.jsonToList(value,type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void del(String key){
        if (key==null){
            return;
        }
        redisTemplate.delete(key);
    }

}
