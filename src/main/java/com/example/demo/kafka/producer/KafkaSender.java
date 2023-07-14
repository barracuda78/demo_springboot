package com.example.demo.kafka.producer;

import java.util.concurrent.Future;

public interface KafkaSender<K, V> {

    Future sendToKafka(String topic, K key, V value);

}
