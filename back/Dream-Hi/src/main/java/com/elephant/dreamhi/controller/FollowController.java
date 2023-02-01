package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
import com.elephant.dreamhi.model.statics.FollowType;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.FollowService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 내 팔로워 수 조회 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @return 나를 팔로우하는 사람 수
     */
    @GetMapping("/api/my/followers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> getFollowerCount(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long response = followService.getFollowerCount(principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), response);
    }

    /**
     * 팔로우 여부 조회
     *
     * @param principalDetails : 현재 접근중인 주체
     * @param type             : 조회할 Type
     * @param id               : 조회할 id
     * @return 팔로우하고 있다면 true, 하지 않다면 false
     */
    @GetMapping("/api/follow")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> checkFollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam FollowType type,
                                            @RequestParam Long id) {
        Boolean response = followService.checkFollow(type, id, principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), response);
    }

    /**
     * 내가 FollowType에 해당하는 id 팔로우 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @param followRequestDto : requestDTO
     * @throws DuplicateKeyException           : 이미 팔로우 중이면 발생
     * @throws DataIntegrityViolationException : DB 제약사항 위반
     */
    @PostMapping("/api/follow")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> addFollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody FollowRequestDto followRequestDto)
            throws DuplicateKeyException {
        Boolean response = followService.addFollow(followRequestDto.getType(), followRequestDto.getId(), principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), response);
    }

    /**
     * 언팔로우 기능 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @param type             : 팔로우할 타입
     * @param id               : 팔로우할 id
     * @throws IllegalArgumentException : 잘못된 접근
     */
    @DeleteMapping("/api/follow")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> removeFollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam FollowType type,
                                             @RequestParam Long id) throws IllegalArgumentException {
        Boolean response = followService.removeFollow(type, id, principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), response);
    }

}
