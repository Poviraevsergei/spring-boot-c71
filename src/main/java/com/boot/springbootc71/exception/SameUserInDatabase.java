package com.boot.springbootc71.exception;

public class SameUserInDatabase extends RuntimeException {
    public SameUserInDatabase(String message) {
        super(message);
    }
}
