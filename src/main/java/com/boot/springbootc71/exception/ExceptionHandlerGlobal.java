package com.boot.springbootc71.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerGlobal {
    @ExceptionHandler(value = {CustomValidException.class, jakarta.validation.ValidationException.class})
    public HttpStatusCode customValidExceptionHandler(Exception exception) {
        log.error("Valid exception: " + exception);
        return HttpStatus.valueOf(400);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public HttpStatusCode usernameNotFoundException(Exception exception) {
        log.error("Username not found: " + exception);
        return HttpStatus.valueOf(401);
    }
}
