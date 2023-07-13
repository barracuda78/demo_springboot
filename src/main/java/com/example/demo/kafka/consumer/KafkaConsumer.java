package com.example.demo.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "channel", groupId = "group_id")
    public String consume(String message) {
        log.info("-----------------------------------message = " + message);
        return message;
    }

}
