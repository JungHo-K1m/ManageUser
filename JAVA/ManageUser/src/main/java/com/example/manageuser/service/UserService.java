package com.example.manageuser.service;

import com.example.manageuser.dto.UserDto;
import com.example.manageuser.dto.UserFormDto;

import java.util.Map;

public interface UserService {
    UserDto createUser(UserFormDto userFormDto);
}
