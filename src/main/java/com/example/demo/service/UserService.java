package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
    User createUser(UserDto user);
    UserDto findById(Long id);
}