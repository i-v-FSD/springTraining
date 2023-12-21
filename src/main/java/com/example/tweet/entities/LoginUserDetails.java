package com.example.tweet.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginUserDetails {

    private String email;
    
    private String passWord;
    
    private  Collection<? extends GrantedAuthority> authorities;
    
    
    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }
    
}
