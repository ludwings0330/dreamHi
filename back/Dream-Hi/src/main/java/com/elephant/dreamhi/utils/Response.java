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

    public static ResponseEntity<Body> noContent() {
        return create(HttpStatus.NO_CONTENT, "no content");
    }

    public static ResponseEntity<Body> created() {
        return create(HttpStatus.CREATED, "created");
    }

    public static ResponseEntity<Body> accepted() {
        return create(HttpStatus.ACCEPTED, "accepted");
    }

    @Builder
    @Getter
    public static class Body {

        private String message;
        private Object result;

    }

}
