package com.testing.logging.Exceptions;

public class SoldOutException extends RuntimeException {

    private String message;

    public SoldOutException(String message) {
        this.message = message;
    }
}
