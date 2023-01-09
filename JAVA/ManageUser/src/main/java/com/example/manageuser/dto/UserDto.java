package com.example.manageuser.dto;

import com.example.manageuser.domain.Role;
import com.example.manageuser.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String userEmail;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String userPw;
    private Role userRole;


    public User toEntity(){
        User user = User.builder()
                .email(userEmail)
                .id(userId)
                .password(userPw)
                .role(userRole)
                .build();
        return user;
    }



    @Builder
    public UserDto(String id, String email, String password, Role role) {
        this.userId = id;
        this.userEmail = email;
        this.userPw = password;
        this.userRole = role;
    }


}
