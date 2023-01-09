package com.example.manageuser.service.impl;

import com.example.manageuser.config.SHA256;
import com.example.manageuser.domain.Role;
import com.example.manageuser.dto.JwtRequestDto;
import com.example.manageuser.dto.UserFormDto;
import com.example.manageuser.entity.User;
import com.example.manageuser.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SHA256 sha256 = new SHA256();
    private final AuthenticationManager authenticationManager;

    public User insertUser(UserFormDto dto) throws NoSuchAlgorithmException {

        Optional<User> byId = userRepository.findById(dto.getUser_id());
        if(byId.isPresent())
            return null;

        User user = new User();
        user.setId(dto.getUser_id());
        user.setEmail(dto.getUser_email());
        SHA256 sha256 = new SHA256();                                       //sha256 암호화
        user.setPassword(sha256.encrypt(sha256.encrypt(dto.getUser_pw()))); //sha256암호화 2번 돌린 비밀번호를 저장.
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public String login(JwtRequestDto request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getId(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailSImpl principal = (UserDetailSImpl) authentication.getPrincipal();
        return principal.getUsername();
    }


}
