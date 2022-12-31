package com.example.tweet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {
    @RequestMapping("/")
    public String topTweetPageString() {
        return "/top.html";
    }
}
