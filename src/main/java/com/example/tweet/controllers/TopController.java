package com.example.tweet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.tweet.entities.Tweet;
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

        List<Tweet> tweetContent = tweetService.fetchTweetList();
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

    @PostMapping("/delete")
    public ModelAndView deleteTweet(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        // 削除ボタン押下されたTweetのIDを利用して削除
        int resultNum = tweetService.deleteTweet(id);
        System.out.println(resultNum + "件のTweetを削除");
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView("/top.html");
        return model;
    }

    @GetMapping("/edit{id}")
    public ModelAndView editProcess(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/edit.html");
        int tweetId = Integer.parseInt(request.getParameter("id"));
        // 編集ボタン押下されたTweetのIDを利用して削除
        Tweet editTargetTweet = tweetService.selectTweetById(tweetId);
        // 遷移先画面を設定
        model.addObject("tweet", editTargetTweet);
        return model;
    }

    @PostMapping("/edit")
    public ModelAndView updateProcess(HttpServletRequest request) {
        int tweetId = Integer.parseInt(request.getParameter("id"));
        String content = (String) request.getParameter("content");
        String updatedMessage = tweetService.updateTweetById(tweetId, content);
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView("/top.html");
        model.addObject("updatedMessage", updatedMessage);
        return model;
    }
}
