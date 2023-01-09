package com.example.manageuser.config;

import com.example.manageuser.handler.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/myinfo").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customLoginSuccessHandler())
                .failureUrl("/fail")
                .and()
                .logout()
                .logoutUrl("/logout");
    }


    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
