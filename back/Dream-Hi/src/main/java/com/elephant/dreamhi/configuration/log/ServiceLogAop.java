package com.elephant.dreamhi.configuration.log;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ServiceLogAop {

    // com.elephant.dreamhi.controller 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.elephant.dreamhi.service..*.*(..))")
    private void logException(){}

    @AfterThrowing(value = "logException()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.error(" => Exception Throw       {} : {} 실행 중 예외 발생!!!!", signature.getDeclaringType().getSimpleName(), method.getName());
        log.error(" => Exception Info        {} : {}", exception.getClass(), exception.getMessage());
    }

}
