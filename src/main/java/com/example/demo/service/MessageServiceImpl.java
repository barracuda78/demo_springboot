package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Long saveMessage(@NonNull String s) {
        final Message message = Message.builder()
                .message(s)
                .build();
        messageRepository.save(message);
        return message.getId();
    }
}
