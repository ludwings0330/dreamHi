package com.elephant.dreamhi.utils;

import lombok.Builder;
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

    @Builder
    @Getter
    public static class Body {

        private String message;
        private Object result;

    }

}
