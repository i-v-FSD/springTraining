package com.example.tweet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tweet.entities.Tweet;
import com.example.tweet.services.TweetService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TopController {
    @Autowired
    private TweetService tweetService;

    @RequestMapping("/")
    public String showIndex(Model model) {
        // 遷移先画面を設定

        List<Tweet> tweetContent = tweetService.fetchTweetList();
        // 画面へ値を渡すため、ModelAndViewに値を詰める
        model.addAttribute("tweetList", tweetContent);
        return "/top.html";
    }

    @PostMapping("/insert")
    public String InsertTweet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String content = (String) request.getParameter("content");

        int resultNum = tweetService.insertTweet(userId, content);
        System.out.println(resultNum + "件のTweetを追加");

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTweet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int id = Integer.parseInt(request.getParameter("id"));
        // 削除ボタン押下されたTweetのIDを利用して削除
        int resultNum = tweetService.deleteTweet(id);
        System.out.println(resultNum + "件のTweetを削除");

        return "redirect:/";
    }

    @GetMapping("/edit{id}")
    public String showEditPage(HttpServletRequest request, Model model) {
        int tweetId = Integer.parseInt(request.getParameter("id"));
        // 編集ボタン押下されたTweetのIDを利用して削除
        Tweet editTargetTweet = tweetService.selectTweetById(tweetId);

        model.addAttribute("tweet", editTargetTweet);
        return "/edit.html";
    }

    @PostMapping("/edit")
    public String editTweet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int tweetId = Integer.parseInt(request.getParameter("id"));
        String content = (String) request.getParameter("content");
        String updatedMessage = tweetService.updateTweetById(tweetId, content);

        redirectAttributes.addFlashAttribute("message", updatedMessage);
        return "redirect:/";
    }
}
