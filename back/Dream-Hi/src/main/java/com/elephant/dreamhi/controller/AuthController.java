package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.JwtResponse;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.TokenService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/api/logout")
    @PreAuthorize("@checker.isLoginUser(#principalDetails)")
    public ResponseEntity<Body> logout(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        tokenService.deleteToken(principalDetails.getId());
        return Response.accepted();
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<Body> reissueAccessToken(@RequestHeader String authorization) {
        JwtResponse jwtResponse = tokenService.reissueAccessToken(authorization);
        return Response.create(HttpStatus.OK, "토큰 재발급 성공", jwtResponse);
    }

}
