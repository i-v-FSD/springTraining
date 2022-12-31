package com.example.tweet.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tweet.services.TweetService;

@Controller
public class TopController {
    @Autowired
    private TweetService tweetService;

    @RequestMapping("/")
    public ModelAndView topTweetPageString() {
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView("/top.html");

        List<Map<String, Object>> tweetContent = tweetService.fetchTweetList();
        // 画面へ値を渡すため、ModelAndViewに値を詰める
        model.addObject("tweetList", tweetContent);
        return model;
    }
}
