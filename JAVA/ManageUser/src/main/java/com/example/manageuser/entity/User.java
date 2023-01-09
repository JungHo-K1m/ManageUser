package com.example.manageuser.entity;


import com.example.manageuser.domain.Role;
import com.example.manageuser.dto.UserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long no;


    @Column(name = "userId")
    private String id;

    @Column(name = "userPw")
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    private Role role;


    @Column(name = "userEmail")
    @Email
    private String email;

    @Builder
    public User(String id, String password, String email, Role role){
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getValue()));
        return roles;
    }

    public static User createUser(UserDto userDto){
        User user = new User();
        user.id = userDto.getUserId();
        user.email = user.getEmail();
        user.password = user.getPassword();
        user.role = Role.USER;
        return user;
    }

    @Override
    public String getUsername(){
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
