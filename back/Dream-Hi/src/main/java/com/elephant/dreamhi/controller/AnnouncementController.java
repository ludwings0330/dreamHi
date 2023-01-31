package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.AnnouncementService;
import com.elephant.dreamhi.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    /**
     * @param announcementId 공고 ID
     * @param user           시큐리티 컨텍스트에 저장된 인증 객체로부터 얻어낸 Principal
     * @return 공고 상세 정보를 응답으로 반환
     * @throws NotFoundException 공고 ID로 공고를 검색했는데 동시성 문제 등의 이슈로 공고를 찾을 수 없게 된 상황에 예외를 던진다.
     */
    @GetMapping("/{announcementId}")
    public ResponseEntity<?> findDetail(@PathVariable Long announcementId, @AuthenticationPrincipal PrincipalDetails user) {
        return announcementService.findDetail(announcementId, user)
                                  .map(dto -> Response.create(HttpStatus.OK, "OK", dto))
                                  .orElseThrow(() -> new NotFoundException("해당 공고를 찾을 수 없습니다."));
    }

    @GetMapping("/{id}/process")
    public ResponseEntity<?> findProcess(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/{id}/castings")
    public ResponseEntity<?> findCastings(@PathVariable Long id) {
        return null;
    }

}
