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

    // tweet全件取得
    public List<Map<String, Object>> findAllTweet() {
        String sql = "SELECT * FROM tweet;";
        List<Map<String, Object>> allTweetList = cnn.queryForList(sql);
        return allTweetList;
    }

    // tweet1件追加（つぶやくに相当）
    public int insertOneTweet(int userId, String tweetContent) {
        String sql = "INSERT INTO tweet(user_id, content) VALUES(?, ?);";
        // 実行結果件数を取得
        int resultNum = cnn.update(sql, userId, tweetContent);
        return resultNum;
    }

    // tweet1件削除
    public int deleteOneTweet(int id) {
        String sql = "DELETE FROM tweet WHERE id = ?;";
        // 実行結果件数を取得
        int resultNum = cnn.update(sql, id);
        return resultNum;
    }
}
