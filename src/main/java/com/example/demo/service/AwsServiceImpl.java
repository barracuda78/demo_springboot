package com.example.demo.service;

import com.example.demo.aws.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsServiceImpl implements AwsService {

    private final PublisherService publisherService;
    @Override
    public void sendToSqs(String message) {
        publisherService.publishEvent(message);
    }

}
