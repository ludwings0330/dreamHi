package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.VolunteerApplyRequestDto;
import com.elephant.dreamhi.model.dto.VolunteerManageRequestDto;
import com.elephant.dreamhi.model.dto.VolunteerSearchCondition;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.VolunteerService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping("/api/announcements/{announcementId}/volunteers")
    @PreAuthorize("@checker.isLoginUser(#user)")
    public ResponseEntity<Body> apply(@PathVariable Long announcementId,
                                      @RequestBody VolunteerApplyRequestDto requestDto,
                                      @AuthenticationPrincipal PrincipalDetails user) throws NotFoundException, IllegalArgumentException {
        requestDto.setUserId(user.getId());
        requestDto.setAnnouncementId(announcementId);

        volunteerService.userApplyOnAnnouncement(requestDto);

        return Response.create(HttpStatus.CREATED, "지원이 완료되었습니다");
    }

    @PutMapping("/api/announcements/{announcementId}/volunteers/{volunteerId}")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasAnnouncementAuthority(#user, #announcementId)")
    public ResponseEntity<Body> manageVolunteer(@PathVariable Long announcementId,
                                                @PathVariable Long volunteerId,
                                                @RequestBody VolunteerManageRequestDto requestDto,
                                                @AuthenticationPrincipal PrincipalDetails user) throws NotFoundException {
        requestDto.setVolunteerId(volunteerId);
        requestDto.setAnnouncementId(announcementId);
        requestDto.setUserId(user.getId());

        volunteerService.manageVolunteer(requestDto);

        return Response.create(HttpStatus.ACCEPTED, "지원자 상태 업데이트 완료");
    }

    @GetMapping("/api/announcements/{announcementId}/casting/{castingId}/volunteers")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasAnnouncementAuthority(#user, #announcementId)")
    public ResponseEntity<Body> findVolunteersByCasting(@PathVariable Long announcementId,
                                                        @PathVariable Long castingId,
                                                        @PageableDefault Pageable pageable,
                                                        @ModelAttribute VolunteerSearchCondition condition,
                                                        @AuthenticationPrincipal PrincipalDetails user) {
        condition.setAnnouncementId(announcementId);
        condition.setCastingId(castingId);
        condition.setPageable(pageable);
        condition.setUserId(user.getId());

        final VolunteerSearchResponseDto responseDto = volunteerService.findVolunteersByCastingIdAndCondition(condition);
        return Response.create(HttpStatus.OK, "조회 성공", responseDto);
    }

    @GetMapping("/api/announcements/{announcementId}/volunteers")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasAnnouncementAuthority(#user, #announcementId)")
    public ResponseEntity<Body> findAllVolunteers(@PathVariable Long announcementId,
                                                  @PageableDefault Pageable pageable,
                                                  @ModelAttribute VolunteerSearchCondition condition,
                                                  @AuthenticationPrincipal PrincipalDetails user) {
        condition.setAnnouncementId(announcementId);
        condition.setUserId(user.getId());
        condition.setPageable(pageable);

        List<VolunteerSearchResponseDto> contents = volunteerService.findAllVolunteers(condition);
        return Response.create(HttpStatus.OK, "조회 성공", contents);
    }

}
