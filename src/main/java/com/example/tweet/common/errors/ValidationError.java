package com.example.tweet.common.errors;

public class ValidationError extends Exception {
    // コンストラクタ
    public ValidationError(String msg) {
        super(msg);
    }
}
