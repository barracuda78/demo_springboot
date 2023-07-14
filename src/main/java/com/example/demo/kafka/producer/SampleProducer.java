package com.example.demo.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class SampleProducer implements KafkaSender<String, String> {

    public Properties createProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    @Override
    public Future sendToKafka(String topic, String key, String value) {
        final KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(createProperties());
        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
        kafkaProducer.close();
        return  future;
    }

}
