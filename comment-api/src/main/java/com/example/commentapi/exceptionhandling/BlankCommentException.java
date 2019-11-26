package com.example.commentapi.exceptionhandling;

public class BlankCommentException extends RuntimeException {
    public BlankCommentException(String msg) {
        super(msg);
    }
}