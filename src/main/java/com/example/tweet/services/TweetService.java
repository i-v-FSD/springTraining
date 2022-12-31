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
}
