package com.boot.springbootc71.exception;

public class SameUserInDatabase extends RuntimeException {
    private final String message;

    public SameUserInDatabase(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Registration problem! We already have user with login: " + message;
    }
}
