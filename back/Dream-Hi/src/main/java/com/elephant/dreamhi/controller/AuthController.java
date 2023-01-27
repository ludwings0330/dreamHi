package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.security.jwt.TokenProvider;
import com.elephant.dreamhi.service.TokenService;
import com.elephant.dreamhi.service.UserService;
import com.elephant.dreamhi.utils.Response;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final TokenService tokenService;

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout() {
        log.info("[LOGOUT] userId: {}", SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.clearContext();
        return Response.ok();
    }

    @PostMapping("/api/refresh-token")
    public ResponseEntity<?> reissueAccessToken(Authentication authentication) throws IllegalArgumentException, SQLException {
        log.info("Authentication : {}", authentication);
        JwtResponse jwtResponse = tokenService.reissueAccessToken(authentication);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), jwtResponse);
    }

}
