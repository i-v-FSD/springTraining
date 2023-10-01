package com.example.tweet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.tweet.entities.User;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate cnn;

    public User findUser(int userId) {
        try {

            String sql = "SELECT name FROM user WHERE id = ?;";
            RowMapper<User> user = new BeanPropertyRowMapper<User>(User.class);
            // 実行結果件数を取得
            User oneUser = cnn.queryForObject(sql, user, userId);
            return oneUser;

        } catch (IncorrectResultSizeDataAccessException ex) {
            System.out.println("[findUser]not found user");
            throw ex;
        } catch (DataAccessException ex) {
            System.out.println("[findUser]occur error");
            throw ex;
        }
    }
}
