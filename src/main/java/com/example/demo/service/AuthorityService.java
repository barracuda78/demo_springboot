package com.example.demo.service;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import lombok.NonNull;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAuthorities(@NonNull Long userId);

}
