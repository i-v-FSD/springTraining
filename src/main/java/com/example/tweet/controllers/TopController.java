package com.example.tweet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tweet.entities.Tweet;
import com.example.tweet.services.TweetService;
import com.example.tweet.common.validation.TweetValidator;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TopController {
    @Autowired
    private TweetService tweetService;

    @RequestMapping("/")
    public ModelAndView topTweetPageString() {
        // 遷移先画面を設定
        ModelAndView model = new ModelAndView();
        try {
            List<Tweet> tweetContent = tweetService.fetchTweetList();
            // 画面へ値を渡すため、ModelAndViewに値を詰める
            model.addObject("tweetList", tweetContent);
            model.setViewName("/top.html");
        } catch (DataAccessException ex) {
            model.addObject("message", "一覧取得時に異常が発生しました。");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            model.setViewName("/errors/errorPage.html");
        } catch (Exception ex) {
            model.addObject("message", "システムエラーが発生しました");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            model.setViewName("/errors/errorPage.html");

        }
        return model;
    }

    @PostMapping("/insert")
    public String InsertTweet(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            TweetValidator validator = new TweetValidator();
            validator.validateInsertItems(request.getParameter("user_id"), request.getParameter("content").trim());

            int userId = Integer.parseInt(request.getParameter("user_id"));
            String content = request.getParameter("content").trim();

            int resultNum = tweetService.insertTweet(userId, content);
            System.out.println(resultNum + "件のTweetを追加");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());
        }

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
    public ModelAndView editProcess(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/edit.html");
        int tweetId = Integer.parseInt(request.getParameter("id"));
        // 編集ボタン押下されたTweetのIDを利用して削除
        Tweet editTargetTweet = tweetService.selectTweetById(tweetId);

        model.addObject("tweet", editTargetTweet);
        return model;
    }

    @PostMapping("/edit")
    public String updateProcess(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        int tweetId = Integer.parseInt(request.getParameter("id"));
        String content = (String) request.getParameter("content");
        String updatedMessage = tweetService.updateTweetById(tweetId, content);

        redirectAttributes.addFlashAttribute("message", updatedMessage);
        return "redirect:/";
    }
}
