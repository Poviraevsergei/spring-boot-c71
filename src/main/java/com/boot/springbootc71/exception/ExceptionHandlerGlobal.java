package com.boot.springbootc71.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerGlobal {
    @ExceptionHandler(value = {CustomValidException.class, jakarta.validation.ValidationException.class})
    public ModelAndView customValidExceptionHandler(Exception exception, HttpServletRequest httpRequest) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errors",exception);
        modelAndView.addObject("url",httpRequest.getRequestURI());
        modelAndView.setViewName("failure");
        modelAndView.setStatus(HttpStatusCode.valueOf(400));
        System.out.println(exception);
        return modelAndView;
    }
}
