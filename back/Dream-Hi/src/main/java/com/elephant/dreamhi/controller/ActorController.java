package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.exception.VisibleException;
import com.elephant.dreamhi.model.dto.ActorProfileDetailDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.ActorService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/auth/actors")
    public ResponseEntity<Body> actorList(@PageableDefault(size = 8) Pageable pageable,
                                       @RequestBody ActorSearchCondition filter) {
        log.info(filter.toString());
        Page<ActorSimpleProfileDto> actors = actorService.findActorsByFilter(filter, pageable);

        return Response.create(HttpStatus.OK, "success", actors);
    }

    @GetMapping("/api/users/{id}/actor-profile")
    public ResponseEntity<Body> getActorProfileDetail(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) throws NotFoundException, VisibleException {
        ActorProfileDetailDto responseDto = actorService.findActorProfileDetail(id, principalDetails);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), responseDto);
    }

    /**
     * 배우 프로필 공개/비공개 전환 메소드
     *
     * @param principalDetails : 현재 접근중인 주체
     * @return 200
     * */
    @PutMapping("/api/my/actor-profile/disclosure")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> changeVisibleProfile(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        actorService.changeVisibleProfile(principalDetails.getId());
        return Response.ok();
    }
}
