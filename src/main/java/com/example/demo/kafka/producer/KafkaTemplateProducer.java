package com.example.demo.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Primary
@Component
@RequiredArgsConstructor
public class KafkaTemplateProducer implements KafkaSender<String, String> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Future sendToKafka(String topic, String key, String value) {
        final CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);
        return future;
    }

}
