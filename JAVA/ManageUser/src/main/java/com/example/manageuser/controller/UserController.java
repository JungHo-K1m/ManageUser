package com.example.manageuser.controller;

import com.example.manageuser.dto.JwtRequestDto;
import com.example.manageuser.dto.UserDto;
import com.example.manageuser.dto.UserFormDto;
import com.example.manageuser.entity.User;
import com.example.manageuser.repository.UserRepository;
import com.example.manageuser.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserFormDto userFormDto) throws NoSuchAlgorithmException {
        // UserDto userDto = userService.createUser(userFormDto);
        User user = userService.insertUser(userFormDto);
        System.out.println("User pw = " + user.getPassword());
            return ResponseEntity.ok().body(user.getPassword());
    }


    @GetMapping("/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/login");
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody JwtRequestDto request) {
        try {

            return userService.login(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
