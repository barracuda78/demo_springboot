package com.example.demo.service;

import com.example.demo.api.dto.KafkaMessageRequestDto;

public interface KafkaClient {

    Long sendMessage(KafkaMessageRequestDto dto);

}
