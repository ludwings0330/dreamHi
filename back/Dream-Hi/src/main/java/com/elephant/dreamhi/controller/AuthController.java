package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.service.UserService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/api/logout")
    public ResponseEntity<?> logout() {
        log.info("[LOGOUT] userId: {}", SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.clearContext();
        return Response.ok();
    }


}
