package com.example.tweet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TweetDao {
    @Autowired
    private JdbcTemplate cnn;

    // 動作確認として文字列を返すだけ
    public List<Map<String, Object>> findAllTweet() {
        String sql = "SELECT * FROM tweet;";
        List<Map<String, Object>> allTweetList = cnn.queryForList(sql);
        return allTweetList;
    }
}
