package com.jhsfully.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("within(com.jhsfully.api.controller.*)")
    private void isApi(){}

    @Around("isApi()")
    private Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info(joinPoint.getSignature().getName() + " "
        + joinPoint.getArgs()[0] + " " + stopWatch.getLastTaskTimeMillis());

        return result;
    }
}
