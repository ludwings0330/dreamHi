package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.UserSimpleDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.UserService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 기본 정보 조회 메소드
     *
     * @param authentication : 현재 로그인한 주체
     * @return UserSimpleDto
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException : id 조회 결과 없을 시 발생
     */
    @GetMapping("/api/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> findUserDetail(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        UserSimpleDto userSimpleDto = userService.findUserSimple(principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), userSimpleDto);
    }

}
