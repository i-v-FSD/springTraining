package com.example.tweet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tweet.common.errors.NoExistRecordError;
import com.example.tweet.dao.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    // つぶやく新規追加時のSQL実行結果件数を返す
    public void createUser(String name, String password) throws NoExistRecordError {
        userDao.createUser(name, password);
    }

}
