package com.example.demo.service;

import com.example.demo.api.dto.KafkaMessageRequestDto;
import com.example.demo.kafka.producer.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl implements KafkaClient {

    private final KafkaSender<String, String> kafkaSender;

    @Override
    public Long sendMessage(KafkaMessageRequestDto dto) {
        kafkaSender.sendToKafka(dto.getTopic(), dto.getKey(), dto.getValue());
        return 42L;
    }

}
