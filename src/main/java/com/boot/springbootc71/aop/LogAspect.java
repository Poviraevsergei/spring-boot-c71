package com.boot.springbootc71.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before(value = "within(com.boot.springbootc71.service.*)")
    public void createStartLog(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature() + " started.");
    }

    @After(value = "execution(public * com.boot.springbootc71.service.*.*(Long))")
    public void createEndLog(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature() + " end.");
    }

    @AfterReturning(value = "within(com.boot.springbootc71.service.*)", returning = "retValue")
    public void logReturnValue(Object retValue) {
        log.info("Return value: " + retValue);
    }

    @AfterThrowing(value = "within(com.boot.springbootc71.service.*)", throwing = "throwValue")
    public void logException(Throwable throwValue) {
        log.error(String.valueOf(throwValue));
    }

    @Around(value = "@annotation(com.boot.springbootc71.aop.TimeAop)")
    public Object logTime(ProceedingJoinPoint jp) throws Throwable {
        LocalTime startTime = LocalTime.now();
        Object o = jp.proceed();
        LocalTime endTime = LocalTime.now();
        log.info("Method worked: " + (endTime.getNano() - startTime.getNano()));
        return o;
    }
}
