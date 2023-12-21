package com.example.tweet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securingweb.LoginUserDetails;
import com.example.tweet.dao.LoginUserDao;
import com.example.tweet.entities.LoginUser;


@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    public LoginUserDao loginUserDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginUser user = loginUserDao.findUser(email);
        if(user == null) {
            throw new UsernameNotFoundException(email+"のユーザーは存在しません");
        }
        return new LoginUserDetails(user);
    }
}
