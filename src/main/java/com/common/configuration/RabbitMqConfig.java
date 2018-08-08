package com.common.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author:artorias
 * @Description:
 * @Date:create in 16:23 2018/7/30 0030
 * Modeified By:
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.queueName}")
    private String queueName;

    @Bean
    public Queue artoriasQueue() {
        return new Queue(queueName);
    }
}
