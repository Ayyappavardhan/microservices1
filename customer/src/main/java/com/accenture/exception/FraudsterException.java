package com.accenture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class FraudsterException extends RuntimeException {
    public FraudsterException(String message) {
        super(message);
    }
}