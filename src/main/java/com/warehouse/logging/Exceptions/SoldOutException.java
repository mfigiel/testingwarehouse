package com.warehouse.logging.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SoldOutException extends RuntimeException {

    private String message;

    public SoldOutException(String message) {
        this.message = message;
    }
}
