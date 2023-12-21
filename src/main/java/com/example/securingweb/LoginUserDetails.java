package com.example.securingweb;

import com.example.tweet.entities.LoginUser;

public class LoginUserDetails {

    private final LoginUser loginUser;

    public LoginUserDetails(LoginUser loginUser){
        this.loginUser = loginUser;
    }

    public String getEmail() {
        return loginUser.email();
    }

    public String getName() {
        return loginUser.name();
    }

    public String getPassWord() {
        return loginUser.password();
    }

}
