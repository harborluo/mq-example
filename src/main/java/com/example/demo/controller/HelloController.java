package com.example.demo.controller;

import com.example.demo.mq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by harbor on 5/9/2019.
 */

@RestController
@RequestMapping(value = "/hello")
@Component
public class HelloController {

    @Autowired
    RabbitMQSender sender;



    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");

    @GetMapping(value = "/greeting/{name}")
    public String sayHello(@PathVariable(name = "name") String personName){
        String message = dateFormat.format(new Date()) +" hello! " + personName;
        sender.send(message);
        return message;
    }

    @GetMapping(value = "/greeting/kafka/{name}")
    public String sayKafkaHello(@PathVariable(name = "name") String personName){
        String message = dateFormat.format(new Date()) +" hello! " + personName;
        sender.send(message);
        return message;
    }

}
