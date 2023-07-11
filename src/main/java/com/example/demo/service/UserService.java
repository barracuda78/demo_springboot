package com.example.demo.service;

import com.example.demo.entity.User;
import lombok.NonNull;

public interface UserService {

    User getByUsername(@NonNull String username);

}
