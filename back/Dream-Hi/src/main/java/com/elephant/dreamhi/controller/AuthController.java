package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.service.TokenService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/api/logout")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> logout() {
        log.info("[LOGOUT] userId: {}", SecurityContextHolder.getContext().getAuthentication().getName());
        SecurityContextHolder.clearContext();
        return Response.ok();
    }

    @PostMapping("/api/refresh-token")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> reissueAccessToken(Authentication authentication) throws IllegalArgumentException {
        JwtResponse jwtResponse = tokenService.reissueAccessToken(authentication);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), jwtResponse);
    }

}
