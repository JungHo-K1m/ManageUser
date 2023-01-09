package com.example.usermanage.entity;


import com.example.usermanage.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity //테이블 생성
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id //PK값
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role userRole;  //사용자 권한

    @Email
    @Column(nullable = false)
    private String userEmail;


    /*
    @Builder
    public User(String userId, String userPw, Role userRole, String userEmail){
        this.userId = userId;
        this.userPw = userPw;
        this.userRole = userRole;
        this.userEmail = userEmail;
    }
    */
}
