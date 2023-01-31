package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.utils.Response;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<?> handle404(Exception exception) {
        log.error("Exception Caused By : {}", exception.getMessage());
        return Response.create(HttpStatus.NOT_FOUND, exception.getMessage());
    }


}
