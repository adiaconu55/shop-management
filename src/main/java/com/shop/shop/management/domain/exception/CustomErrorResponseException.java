package com.shop.shop.management.domain.exception;

public class CustomErrorResponseException extends RuntimeException {

    private final String errorMessage;

    public CustomErrorResponseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
