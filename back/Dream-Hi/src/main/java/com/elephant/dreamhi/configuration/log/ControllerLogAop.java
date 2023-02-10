package com.elephant.dreamhi.configuration.log;

import com.elephant.dreamhi.utils.Response.Body;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ControllerLogAop {

    // com.elephant.dreamhi.controller 이하 패키지의 모든 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.elephant.dreamhi.controller.*Controller.*(..))")
    private void cut(){}

    // Pointcut에 의해 필터링된 경로로 들어오는 경우 메서드 호출 전에 적용
    @Before("cut()")
    public void beforeParameterLog(JoinPoint joinPoint) {
        // 메서드 정보 받아오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.info("[{}.{}]---------------------------------------------------------------------------------------------------------------------------------------------------------------------------",signature.getDeclaringType().getSimpleName(), method.getName());
        Parameter[] params = method.getParameters();
        Object[] args = joinPoint.getArgs();
        // 파라미터 받아오기
        for (int i=0; i<params.length; i++) {
            log.info(" => Request Data    {} : {}", params[i].getName(), args[i]);
        }
    }

    // Poincut에 의해 필터링된 경로로 들어오는 경우 메서드 리턴 후에 적용
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturnLog(JoinPoint joinPoint, Object returnObj) {
        // 메서드 정보 받아오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if(returnObj instanceof ResponseEntity) {
            ResponseEntity<Body> response = (ResponseEntity<Body>) returnObj;
            // return 파라미터
            HttpStatus status = response.getStatusCode();
            String message = status.equals(HttpStatus.OK) ? response.getBody().getMessage() : "";
            Object result = status.equals(HttpStatus.OK) ? response.getBody().getResult() : "";
            log.info(" => Response Data    {} : {} \t{}", status, message, result);
        }
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @AfterThrowing(value = "cut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.error(" => Exception Throw       {} : {} 실행 중 예외 발생!!!!", signature.getDeclaringType().getSimpleName(), method.getName());
        log.error(" => Exception Info        {} : {}", exception.getClass(), exception.getMessage());

        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
