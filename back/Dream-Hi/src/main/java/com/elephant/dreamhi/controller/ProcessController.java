package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.ProcessService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements/process")
@RequiredArgsConstructor
@Slf4j
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/begin")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> saveProcessWithRecruiting(
            @RequestParam Long announcementId,
            @RequestParam Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        processService.saveProcessWithRecruiting(announcementId);
        return Response.create(HttpStatus.CREATED, "오디션이 시작되었습니다.");
    }

    @PostMapping("/next")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #processSaveDto.producerId)")
    public ResponseEntity<Body> saveProcessWithoutRecruiting(
            @RequestBody @Valid ProcessSaveDto processSaveDto,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws IllegalArgumentException {
        if (processSaveDto.getState() == ProcessState.RECRUITING) {
            throw new IllegalArgumentException("Process의 state가 RECRUITING인 경우는 공고 생성 시 한 번만 저장될 수 있습니다.");
        }

        processService.saveProcessWithoutRecruiting(processSaveDto);
        return Response.create(HttpStatus.CREATED, "오디션의 다음 단계가 시작되었습니다.");
    }

}
