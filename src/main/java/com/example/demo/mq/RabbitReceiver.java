package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by harbor on 5/9/2019.
 */
@Component
@RabbitListener(queues = "hello-queue")
public class RabbitReceiver {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RabbitHandler
    public void process(String message){
        jdbcTemplate.update("insert into mq_message(time_stamp,message) values(sysdate,?)", message);
//        System.out.println("Receive message: " + message);
    }

}
