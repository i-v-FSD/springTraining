package com.example.tweet.common.validation;

import java.util.regex.Pattern;

import com.example.tweet.common.errors.ValidationError;

public class TweetValidator {
    public void validateInsertItems(String id, String content) throws ValidationError {
        validateId(id);
        validateContent(content);
    }

    private void validateId(String userId) throws ValidationError {
        String regString = "[1-9][0-9]{0,8}";
        if (!Pattern.matches(regString, userId)) {
            throw new ValidationError("8桁以下の整数で入力してください");
        }
    }

    private void validateContent(String content) throws ValidationError {
        if (content.length() == 0) {
            throw new ValidationError("contentを入力してください");
        } else if (content.length() > 140) {
            throw new ValidationError("140文字以下で入力してください");
        }
    }
}
