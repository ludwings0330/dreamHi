package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.FullResourceException;
import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorListResponseDto;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorProfileRequestDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.ActorService;
import com.elephant.dreamhi.service.StyleService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActorController {

    private final ActorService actorService;
    private final StyleService styleService;

    /**
     * ActorList 조회 - With Filtering 메소드
     *
     * @param filter           : filtering 조건 Dto
     * @param pageable         : paging 정보
     * @param principalDetails : 현재 접근중인 주체
     * @return 200 : ActorListResponseDto
     * @return 204 : 빈 리스트일 경우 반환
     */
    @GetMapping("/api/actors")
    public ResponseEntity<Body> actorList(@PageableDefault(size = 8) Pageable pageable,
                                          @ModelAttribute ActorSearchCondition filter,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        filter.setId(principalDetails.getId());
        Page<ActorListResponseDto> actors = actorService.findActorsByFilter(filter, pageable);
        if(actors.getContent().size() == 0) return Response.noContent();
        return Response.create(HttpStatus.OK, "배우 목록 조회 성공", actors);
    }

    @GetMapping("/api/users/{id}/actor-profile")
    public ResponseEntity<Body> getActorProfileDetail(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails)
            throws NotFoundException, VisibleException {
        ActorProfileDetailDto responseDto = actorService.findActorProfileDetail(id, principalDetails.getId());
        return Response.create(HttpStatus.OK, "배우 프로필 상세 조회 성공", responseDto);
    }

    /**
     * 배우 프로필 공개/비공개 전환 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @return 200
     */
    @PutMapping("/api/my/actor-profile/disclosure")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> changeVisibleProfile(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        actorService.changeVisibleProfile(principalDetails.getId());
        return Response.accepted();
    }

    /**
     * 배우 프로필 수정 메소드
     *
     * @param principalDetails       : 현재 접근중인 주체
     * @param actorProfileRequestDto
     * @return 200
     * @throws FullResourceException           : 태그 저장 개수 초과 시 발생
     * @throws DataIntegrityViolationException : Unique 제약 조건 위반 시 발생
     */
    @PutMapping("/api/my/actor-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> updateActorProfile(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                   @RequestBody ActorProfileRequestDto actorProfileRequestDto)
            throws FullResourceException, DataIntegrityViolationException {
        actorService.updateActorProfile(principalDetails.getId(), actorProfileRequestDto);
        styleService.updateActorStyleTags(actorProfileRequestDto);
        return Response.accepted();
    }

}
