package com.example.manageuser.service.impl;

import com.example.manageuser.domain.Role;
import com.example.manageuser.dto.UserDto;
import com.example.manageuser.dto.UserFormDto;
import com.example.manageuser.entity.User;
import com.example.manageuser.repository.UserRepository;
import com.example.manageuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.manageuser.domain.Role.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserFormDto userFormDto){
        if(userRepository.findByEmail(userFormDto.getUserEmail()) != null){
            return null;
        }
        User user = userRepository.save(User.builder()
                .password(bCryptPasswordEncoder.encode(userFormDto.getUserPw()))
                .email(userFormDto.getUserEmail())
                .role(USER).build());

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
