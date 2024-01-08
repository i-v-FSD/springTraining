package com.example.tweet.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.tweet.common.errors.NoExistRecordError;
import com.example.tweet.entities.Tweet;
import com.example.tweet.entities.User;

@Repository
public class TweetDao {
    private final JdbcTemplate jdbc;

    @Autowired
    public TweetDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // tweet全件取得
    public List<Tweet> findAllTweet() {
        String sql = "SELECT * FROM tweet " +
                "JOIN user ON tweet.user_id = user.id;";
        try {
            return jdbc.query(sql, (resultSet, rowNum) -> {
                Tweet tweet = new Tweet();
                tweet.setId(resultSet.getInt("id"));
                tweet.setUserId(resultSet.getInt("user_id"));
                tweet.setContent(resultSet.getString("content"));

                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                tweet.setUser(user);

                return tweet;
            });
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
