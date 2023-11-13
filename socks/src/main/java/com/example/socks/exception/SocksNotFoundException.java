package com.example.socks.exception;

public class SocksNotFoundException extends RuntimeException {

    public SocksNotFoundException(String message) {
        super(message);
    }
}
