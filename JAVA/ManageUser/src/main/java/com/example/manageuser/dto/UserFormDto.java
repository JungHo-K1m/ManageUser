package com.example.manageuser.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;


@Getter
@Setter
public class UserFormDto {

    private String user_id;

    private String user_email;

    private String user_pw;
}
