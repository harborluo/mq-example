package com.example.demo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by harbor on 11/1/2018.
 */
@Component
public class KafkaReceiver {

    private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

//    @Autowired
//    NotificationClient client;

    @KafkaListener(topics = {"${app.topic.hello}"})
    public void processLog(@Payload String message){
        logger.info("Receive message '{}'", message);
//        client.sendMessage2Dev(message);
    }

}
