package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({ DuplicateKeyException.class })
    public ResponseEntity<Body> handleConflict(Exception e) {
        return Response.create(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Body> handleNotFound(Exception e) {
        return Response.create(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Body> handleBadRequest(Exception e) {
        return Response.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Body> handleForbidden(Exception e) {
        return Response.create(HttpStatus.FORBIDDEN, e.getMessage());
    }

}
