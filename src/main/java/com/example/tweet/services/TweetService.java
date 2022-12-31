package com.example.tweet.services;

import org.springframework.stereotype.Service;

import com.example.tweet.dao.TweetDao;

public class TweetService {
    TweetDao tweetDao = new TweetDao();

    // 動作確認としてDaoメソッドの戻り値を返すだけ
    public String fetchTweetList() {
        String allTweet = tweetDao.findAllTweet();
        return allTweet;
    }
}
