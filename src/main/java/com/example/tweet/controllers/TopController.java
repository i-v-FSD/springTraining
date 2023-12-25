package com.example.tweet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tweet.entities.Tweet;
import com.example.tweet.services.TweetService;
import com.example.tweet.common.errors.NoExistRecordError;
import com.example.tweet.common.validation.TweetValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.ValidationException;

@Controller
public class TopController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/")
    public String showInitialPage() {
        return "redirect:/top";
    }

    @GetMapping("top")
    public String topTweetPageString(Model model) {
        try {
            List<Tweet> tweetContent = tweetService.fetchTweetList();
            // 画面へ値を渡すため、ModelAndViewに値を詰める
            model.addAttribute("tweetList", tweetContent);
            return "/top.html";
        } catch (DataAccessException ex) {
            model.addAttribute("message", "一覧取得時に異常が発生しました。");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            model.addAttribute("message", "システムエラーが発生しました");
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
        return "/errors/errorPage.html";
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

        } catch (ValidationException ex) {
            redirectAttributes.addFlashAttribute("validMessage", ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (DataAccessException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (NoExistRecordError ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());
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
        return "redirect:/top";
    }
}
