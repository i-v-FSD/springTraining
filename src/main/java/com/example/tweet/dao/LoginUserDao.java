package com.example.tweet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.tweet.entities.LoginUser;

@Repository
public class LoginUserDao {

    private final String sql = "SELECT email,name,password FROM user WHERE email = ?";

    @Autowired
    private JdbcTemplate jdbc;

    public LoginUser findUser(String email) {
        RowMapper<LoginUser> rowMapper = new BeanPropertyRowMapper<LoginUser>(LoginUser.class);
        return jdbc.queryForObject(sql,rowMapper,email);
    }

}
