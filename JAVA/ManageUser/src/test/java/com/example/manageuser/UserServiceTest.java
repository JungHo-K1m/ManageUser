package com.example.manageuser;


import com.example.manageuser.dto.UserDto;
import com.example.manageuser.entity.User;
import com.example.manageuser.service.UserService;
import com.example.manageuser.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserServiceImpl userService;

    public User createUser(){
        UserDto userDto = new UserDto();
        userDto.setUserId("jungho");
        userDto.setUserPw("asdqwe123!@#");
        userDto.setUserEmail("qopqop55@naver.com");
        return User.createUser(userDto);
    }


    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest(){
        User user = createUser();

    }
}
