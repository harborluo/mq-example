package com.example.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by harbor on 5/9/2019.
 */
@Component
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend("hello-queue",message);
    }

}
