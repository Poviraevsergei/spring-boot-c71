package com.boot.springbootc71.exception;

public class CustomValidException extends RuntimeException {
    private String message;

    public CustomValidException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Problem with validation ! Exceptions: " + message;
    }
}
