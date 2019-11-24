package com.example.userapi.exceptionhandling;

public class EmailSignupException extends RuntimeException {
    public EmailSignupException(String msg) {
        super(msg);
    }
}
