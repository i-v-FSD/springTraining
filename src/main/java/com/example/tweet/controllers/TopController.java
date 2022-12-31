package com.example.tweet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tweet.services.TweetService;

@Controller
public class TopController {
    @RequestMapping("/")
    public ModelAndView topTweetPageString() {
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView("/top.html");

        TweetService tweetService = new TweetService();
        String tweetContent = tweetService.fetchTweetList();
        // 画面へ値を渡すため、ModelAndViewに値を詰める
        model.addObject("tweet", tweetContent);
        return model;
    }
}
