package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.repository.AuthorityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl {

    private final AuthorityRepository authorityRepository;

    public List<Authority> getAuthorities(@NonNull Long userId) {
        return authorityRepository.findByUsers_id(userId);
    }

}
