package com.common.redis.service;

import java.util.List;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 14:35 2018/7/30 0030
 * Modeified By:
 */
public interface RedisService {
    void  set(String key,String value);
    void  set(String key,String value,Long seconds);
    String get(String key);
    <T> T get(String key,Class<T> t);
    <T> List<T> getList(String key, Class<T> type);
    void del(String key);
}
