package com.juandavyc.university.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Before("execution(* com.juandavyc.university.repositories.*.*(..))")
//    public void logMethodCall(final JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object entity = joinPoint.getArgs()[0].getClass();
//
//        log.info("Executed method: {}, entity: {} ", methodName,entity);
//    }
//
//    @AfterThrowing(pointcut = "execution(* com.juandavyc.university.repositories.*.*(..))", throwing = "ex")
//    public void logException(final JoinPoint joinPoint, final Throwable ex) {
//        String methodName = joinPoint.getSignature().getName();
//        log.error("log error: executed method: {}, exception: {} ", methodName, ex.getMessage());
//    }
}
