package com.example.manageuser.dto;

import com.example.manageuser.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userEmail;
    private String userPw;
    private Role userRole;

    @Builder
    public UserDto(String id, String email, String password, Role role) {
        this.userId = id;
        this.userEmail = email;
        this.userPw = password;
        this.userRole = role;
    }
}
