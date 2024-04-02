package com.boot.springbootc71.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerGlobal {
    @ExceptionHandler(value = {CustomValidException.class, jakarta.validation.ValidationException.class})
    public HttpStatusCode customValidExceptionHandler(Exception exception) {
        System.out.println(exception); //TODO: logger
        return HttpStatus.valueOf(400);
    }
}
