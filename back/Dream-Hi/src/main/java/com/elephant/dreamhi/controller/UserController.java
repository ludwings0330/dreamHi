package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.PictureDto;
import com.elephant.dreamhi.model.dto.UserSimpleDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.UserService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 내 회원 기본 정보 조회 메소드
     *
     * @param principalDetails : 현재 로그인한 주체
     * @return UserSimpleDto
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException : id 조회 결과 없을 시 발생
     */
    @GetMapping("/api/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> findUserDetail(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserSimpleDto userSimpleDto = userService.findUserSimple(principalDetails.getId());
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), userSimpleDto);
    }

    /**
     * 메인 프로필 사진 변경 메소드
     *
     * @param id         : userId
     * @param pictureDto : PictureDTO
     * @return 200
     * @throws UsernameNotFoundException : userId에 해당하는 유저가 존재하지않은 경우 발생합니다.
     */
    @PostMapping("/api/users/{id}/main-profile")
    @PreAuthorize("hasRole('ROLE_USER') and principal.id == #id")
    public ResponseEntity<Body> changeMainProfile(@PathVariable Long id, @RequestBody PictureDto pictureDto) throws UsernameNotFoundException {
        log.info("id : {} picture : {}"  , id, pictureDto);
        userService.updateMainProfile(id, pictureDto);
        return Response.ok();
    }

}
