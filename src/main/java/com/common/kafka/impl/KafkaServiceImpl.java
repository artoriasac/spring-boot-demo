package com.common.kafka.impl;

import com.common.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 10:25 2018/8/10 0010
 * Modeified By:
 */
@Service
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(String topic, String key, String value) {
        if (key==null||key.isEmpty()){
            kafkaTemplate.send(topic,value);
        }else {
            kafkaTemplate.send(topic,key,value);
        }
    }
}
