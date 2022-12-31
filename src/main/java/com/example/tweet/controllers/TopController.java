package com.example.tweet.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tweet.services.TweetService;

import jakarta.servlet.http.HttpServletRequest;

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

    @PostMapping("/insert")
    public ModelAndView InsertTweet(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String content = (String) request.getParameter("content");

        int resultNum = tweetService.insertTweet(userId, content);
        System.out.println(resultNum + "件のTweetを追加");
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView("/top.html");

        return model;
    }
}
