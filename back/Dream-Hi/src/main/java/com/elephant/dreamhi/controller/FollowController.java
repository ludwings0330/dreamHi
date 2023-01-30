package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.FollowService;
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
public class FollowController {

    private final FollowService followService;

    /**
     * 내 팔로워 수 조회 메소드
     *
     * @param authentication : 현재 접근중인 주체
     * @return 나를 팔로우하는 사람 수
     */
    @GetMapping("/api/my-followers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getFollowerCount(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long response = followService.getFollowerCount(principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), response);
    }

}
