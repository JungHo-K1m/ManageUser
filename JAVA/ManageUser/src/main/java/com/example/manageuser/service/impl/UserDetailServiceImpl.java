package com.example.manageuser.service.impl;

import com.example.manageuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return Optional
                .ofNullable(userRepository.findById(userId).get())
                .orElseThrow(()-> new BadCredentialsException("등록되지 않은 사용자입니다."));
    }

}
