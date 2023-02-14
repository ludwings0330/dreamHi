package com.elephant.dreamhi.utils;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    private Response() {
    }

    public static ResponseEntity<Body> create(HttpStatus httpStatus, String message) {
        return Response.create(httpStatus, message, null);
    }

    public static ResponseEntity<Body> create(HttpStatus httpStatus, String message, Object result) {

        return new ResponseEntity<>(
                Body.builder()
                    .message(message)
                    .result(result)
                    .build(),
                httpStatus);
    }

    public static ResponseEntity<Body> ok() {
        return create(HttpStatus.OK, "ok", null);
    }

    public static ResponseEntity<Body> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<Body> created() {
        return create(HttpStatus.CREATED, "created");
    }

    public static ResponseEntity<Body> accepted() {
        return create(HttpStatus.ACCEPTED, "accepted");
    }

    public static ResponseEntity<Body> accepted(Object result) {
        return create(HttpStatus.ACCEPTED, "accepted", result);
    }

    @Builder
    @Getter
    @Data
    public static class Body {

        private String message;
        private Object result;

    }

}
