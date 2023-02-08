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

    /**
     * 공고 모집 마감 시 새로운 절차를 등록하고, 공고를 연결한다.
     *
     * @param announcementId 모집 마감인 공고
     * @param producerId     공고를 작성한 제작사의 ID
     * @param user           현재 로그인한 유저, 제작사에서 EDITOR 권한을 갖고 있어야 한다.
     * @return 공고의 절차를 바꾸는 것에 성공하면 201 코드로 응답을 반환
     */
    @PostMapping("/begin")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> saveProcessWithRecruiting(
            @RequestParam Long announcementId,
            @RequestParam Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        processService.saveProcessWithRecruiting(announcementId);
        return Response.create(HttpStatus.CREATED, "오디션이 시작되었습니다.");
    }

    /**
     * 오디션 진행 중 또는 캐스팅 완료 시 새로운 절차를 등록하고, 공고를 연결한다.
     *
     * @param processSaveDto 공고ID, 제작사ID, 공고의 현재 절차, 오디션 현재 단계를 저장
     * @param user           현재 로그인한 유저, 제작사에서 EDITOR 권한을 가지고 있어야 한다.
     * @return 오디션의 다음 절차를 등록하는 데 성공하면 201 코드로 응답을 반환
     * @throws IllegalArgumentException 공고의 현재 절차가 모집 중인 경우 발생하는 예외
     */
    @PostMapping("/next")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasEditorAuthority(#user, #processSaveDto.producerId)")
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
