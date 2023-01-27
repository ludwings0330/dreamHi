package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.UserDetailDto;
import com.elephant.dreamhi.service.UserService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 기본 정보 조회 메소드
     *
     * @param id : PathVariable userId [index]
     * @return UserDetailDto
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException : id 조회 결과 없을 시 발생
     */
    @GetMapping("/auth/users/{id}")
    public ResponseEntity<?> findUserDetail(@PathVariable("id") Long id) {
        UserDetailDto userDetailDto = userService.findUserDetail(id);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), userDetailDto);
    }

}
