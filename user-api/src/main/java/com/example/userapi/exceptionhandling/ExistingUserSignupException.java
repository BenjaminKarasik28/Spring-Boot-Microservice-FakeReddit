package com.example.userapi.exceptionhandling;

public class ExistingUserSignupException extends RuntimeException {
    public ExistingUserSignupException(String msg) {
        super(msg);
    }
}