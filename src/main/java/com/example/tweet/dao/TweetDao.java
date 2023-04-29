package com.example.tweet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.tweet.entities.Tweet;

@Repository
public class TweetDao {
    @Autowired
    private JdbcTemplate cnn;

    // tweet全件取得
    public List<Tweet> findAllTweet() {
        String sql = "SELECT * FROM tweet;";
        List<Map<String, Object>> allTweetList = cnn.queryForList(sql);

        List<Tweet> tweetList = new ArrayList<>();
        for (Map<String, Object> tweetInfo : allTweetList) {
            Tweet tweet = new Tweet();
            tweet.setId((int) tweetInfo.get("id"));
            tweet.setUserId((int) tweetInfo.get("user_id"));
            tweet.setContent((String) tweetInfo.get("content"));
            tweetList.add(tweet);
        }
        return tweetList;

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

    public Tweet selectTweetById(int tweetId) {
        String sql = "SELECT id, content FROM tweet WHERE id = ?";
        // 取得データをTweet型にマッピング
        RowMapper<Tweet> tweet = new BeanPropertyRowMapper<Tweet>(Tweet.class);
        return cnn.queryForObject(sql, tweet, tweetId);
    }

}
