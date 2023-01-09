package com.example.manageuser.config;

import com.example.manageuser.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final SHA256 sha256;
    String insertPw;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        try {
            insertPw = sha256.encrypt(sha256.encrypt(password));
            if(insertPw.equals(sha256.encrypt(sha256.encrypt(userDetails.getPassword())))){
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
