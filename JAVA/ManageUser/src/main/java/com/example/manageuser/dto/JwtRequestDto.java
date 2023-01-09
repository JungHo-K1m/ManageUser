package com.example.manageuser.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {

    private String id;
    private String password;
}