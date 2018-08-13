package com.common.rabbitMq.impl;

import com.common.rabbitMq.RabbitMqService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 16:31 2018/7/30 0030
 * Modeified By:
 */
@Service
public class RabbitMqServiceImpl implements RabbitMqService{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queueName}")
    private String queueName;

    @Override
    public void send(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
