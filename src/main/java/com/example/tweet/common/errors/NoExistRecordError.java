package com.example.tweet.common.errors;

public class NoExistRecordError extends Exception {
    public NoExistRecordError(String msg) {
        super(msg);
    }
}
