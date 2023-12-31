package com.example.tweet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.tweet.common.errors.NoExistRecordError;
import com.example.tweet.entities.Tweet;

@Repository
public class TweetDao {
    @Autowired
    private JdbcTemplate jdbc;

    // tweet全件取得
    public List<Tweet> findAllTweet() {
        String sql = "SELECT * FROM tweet;";
        try {
            List<Map<String, Object>> allTweetList = jdbc.queryForList(sql);

            List<Tweet> tweetList = new ArrayList<>();
            for (Map<String, Object> tweetInfo : allTweetList) {
                Tweet tweet = new Tweet();
                tweet.setId((int) tweetInfo.get("id"));
                tweet.setUserId((int) tweetInfo.get("user_id"));
                tweet.setContent((String) tweetInfo.get("content"));
                tweetList.add(tweet);
            }
            return tweetList;
        } catch (DataAccessException ex) {
            System.out.println("[findAllTweet]occur error");
            throw ex;
        }
    }

    // tweet1件追加（つぶやくに相当）
    public int insertOneTweet(int userId, String tweetContent) throws NoExistRecordError {
        try {

            String sql = "INSERT INTO tweet(user_id, content) VALUES(?, ?);";
            // 実行結果件数を取得
            int resultNum = jdbc.update(sql, userId, tweetContent);
            if (resultNum == 0) {
                throw new NoExistRecordError("No tweets to update");
            }
            return resultNum;
        } catch (DataAccessException ex) {
            System.out.println("[findAllTweet]occur error");
            throw ex;
        }
    }

    // tweet1件削除
    public int deleteOneTweet(int id) {
        String sql = "DELETE FROM tweet WHERE id = ?;";
        // 実行結果件数を取得
        int resultNum = jdbc.update(sql, id);
        return resultNum;
    }

    public Tweet selectTweetById(int tweetId) {
        String sql = "SELECT id, content FROM tweet WHERE id = ?";
        // 取得データをTweet型にマッピング
        RowMapper<Tweet> tweet = new BeanPropertyRowMapper<Tweet>(Tweet.class);
        return jdbc.queryForObject(sql, tweet, tweetId);
    }

    public int updateTweetById(int tweetId, String content) {
        String sql = "UPDATE tweet SET content = ? WHERE id = ?";
        Object[] params = { content, tweetId };
        // 実行結果件数を取得
        int resultNum = jdbc.update(sql, params);
        return resultNum;
    }
}
