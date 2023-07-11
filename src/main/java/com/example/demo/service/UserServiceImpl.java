package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("user with name %s not found", username)));
    }
}
