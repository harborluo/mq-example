package com.example.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by harbor on 11/1/2018.
 */
@Service
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message){

        logger.info("sending message='{}' to topic='{}'", message, topic);

        try {
            kafkaTemplate.send(topic, message);
        }catch (Throwable e){
            logger.error("Fail to send message '{}' to kafka.", message, e);
            e.printStackTrace();
        }

    }
}
