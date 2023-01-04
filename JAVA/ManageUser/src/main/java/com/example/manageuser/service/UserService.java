package com.example.manageuser.service;

import com.example.manageuser.dto.UserDto;
import com.example.manageuser.dto.UserFormDto;

public interface UserService {
    UserDto createUser(UserFormDto userFormDto);
}
