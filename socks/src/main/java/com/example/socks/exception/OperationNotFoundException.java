package com.example.socks.exception;

public class OperationNotFoundException extends RuntimeException{
    public OperationNotFoundException(String message) {
        super(message);
    }
}
