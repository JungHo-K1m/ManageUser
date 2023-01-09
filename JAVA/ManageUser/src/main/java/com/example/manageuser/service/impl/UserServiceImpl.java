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

    public User saveUser(User user){
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        /*User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원임다.");
        }*/
    }

    @Override
    public UserDto createUser(UserFormDto userFormDto){
        /*User findByEmail = userRepository.findByEmail(userFormDto.getUser_email());
        if(findByEmail != null){
            return null;
        }
        User user = userRepository.save(User.builder()
                .password(userFormDto.getUser_pw())
                .email(userFormDto.getUser_email())
                .role(USER).build());

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
                */
        return new UserDto();
    }
}
