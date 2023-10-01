package com.example.tweet.common.validation;

import java.util.regex.Pattern;

import jakarta.xml.bind.ValidationException;

public class TweetValidator {
    public void validateInsertItems(String id, String content) throws ValidationException {
        validateId(id);
        validateContent(content);
    }

    private void validateId(String userId) throws ValidationException {
        String regString = "[1-9][0-9]{0,7}";
        if (!Pattern.matches(regString, userId)) {
            throw new ValidationException("8桁以下の整数で入力してください");
        }
    }

    private void validateContent(String content) throws ValidationException {
        if (content.length() == 0) {
            throw new ValidationException("contentを入力してください");
        } else if (content.length() > 140) {
            throw new ValidationException("140文字以下で入力してください");
        }
    }
}
