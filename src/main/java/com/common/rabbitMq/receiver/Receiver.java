package com.common.rabbitMq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 16:37 2018/7/30 0030
 * Modeified By:
 */
@Component
@RabbitListener(queues = "artorias")
public class Receiver {
    @RabbitHandler
    public void process(String message) {
        System.out.println("Receiver : " + message);
    }

}