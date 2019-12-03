package com.example.postapi.exceptionhandling;

public class BlankPostException extends RuntimeException {
    public BlankPostException(String msg) {
        super(msg);
    }
}
