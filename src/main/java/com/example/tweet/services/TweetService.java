package com.example.tweet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tweet.dao.TweetDao;
import com.example.tweet.entities.Tweet;

@Service
public class TweetService {
    @Autowired
    private TweetDao tweetDao;

    // 動作確認としてDaoメソッドの戻り値を返すだけ
    public List<Tweet> fetchTweetList() {
        List<Tweet> allTweetList = tweetDao.findAllTweet();
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

    // つぶやく編集対象となるTweet1件の情報を返す
    public Tweet selectTweetById(int tweetId) {
        return tweetDao.selectTweetById(tweetId);
    }
}
