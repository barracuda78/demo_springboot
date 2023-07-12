package com.example.demo.service;

import com.example.demo.api.dto.KafkaMessageRequestDto;
import com.example.demo.kafka.producer.SampleProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClientImpl implements KafkaClient {

    private final SampleProducer sampleProducer;
    @Override
    public Long sendMessage(KafkaMessageRequestDto dto) {
        sampleProducer.sendToKafka(dto.getTopic(), dto.getKey(), dto.getValue());
        return 42L;
    }

}
