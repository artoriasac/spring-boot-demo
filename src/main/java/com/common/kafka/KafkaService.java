package com.common.kafka;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 10:25 2018/8/10 0010
 * Modeified By:
 */
public interface KafkaService {
    void send(String topic,String key,String value);
}
