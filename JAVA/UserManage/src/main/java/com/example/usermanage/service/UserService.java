package com.example.usermanage.service;

import com.example.usermanage.entity.User;
import com.example.usermanage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * 회원가입
     */
    public String join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findOne(user.getUserId());
        if(userRepository != null){
            throw new IllegalStateException("이미 존재");
        }
    }
}
