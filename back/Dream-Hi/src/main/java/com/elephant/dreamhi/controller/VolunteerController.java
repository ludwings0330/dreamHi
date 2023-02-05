package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.VolunteerApplyRequestDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.VolunteerService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping("/api/announcements/{announcementId}/volunteers")
    public ResponseEntity<Body> apply(@PathVariable Long announcementId,
                                      @RequestBody VolunteerApplyRequestDto requestDto,
                                      @AuthenticationPrincipal PrincipalDetails user) {
        requestDto.setUserId(user.getId());
        requestDto.setAnnouncementId(announcementId);
        log.info("지원 요청 : {}", requestDto);
        volunteerService.userApplyOnAnnouncement(requestDto);

        return Response.create(HttpStatus.CREATED, "지원이 완료되었습니다");
    }

}
