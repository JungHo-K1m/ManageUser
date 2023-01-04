package com.example.manageuser.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;


@Getter
@Setter
public class UserFormDto {
    @NotNull
    @Email
    private  String userEmail;

    @NotNull
    private String userPw;
}
