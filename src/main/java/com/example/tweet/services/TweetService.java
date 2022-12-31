package com.example.tweet.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tweet.dao.TweetDao;

@Service
public class TweetService {
    @Autowired
    private TweetDao tweetDao;

    // 動作確認としてDaoメソッドの戻り値を返すだけ
    public List<Map<String, Object>> fetchTweetList() {
        List<Map<String, Object>> allTweetList = tweetDao.findAllTweet();
        return allTweetList;
    }

    // つぶやく新規追加時のSQL実行結果件数を返す
    public int insertTweet(int userId, String tweetContent) {
        int resultNum = tweetDao.insertOneTweet(userId, tweetContent);
        return resultNum;
    }

    // つぶやく削除時のSQL実行結果件数を返す
    public int deleteTweet(int id) {
        int resultNum = tweetDao.deleteOneTweet(id);
        return resultNum;
    }
}
