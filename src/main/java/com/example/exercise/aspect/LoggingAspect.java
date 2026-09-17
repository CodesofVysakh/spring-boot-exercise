package com.example.exercise.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all controller methods
    @Pointcut("execution(* com.example.exercise.controller..*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info(">>> REQUEST  | {} | args={}", method, Arrays.toString(args));

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long took = System.currentTimeMillis() - start;
            logger.info("<<< RESPONSE | {} | time={}ms | result={}", method, took, result);
            return result;
        } catch (Throwable ex) {
            long took = System.currentTimeMillis() - start;
            logger.error("!!! ERROR   | {} | time={}ms | message={}", method, took, ex.getMessage(), ex);
            throw ex;
        }
    }
}