package com.common.kafka;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 10:25 2018/8/10 0010
 * Modeified By:
 */
public interface KafkaService {
    /**
     *@Author : artorias
     *@Description :
     *@Date : 2018/8/10 0010
     *@param topic
     *@param key
     *@param value
     */
    void send(String topic,String key,String value);
}
