package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.SessionService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements/{announcementId}/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/{sessionId}")
    public ResponseEntity<Body> saveSession(@PathVariable Long announcementId, @PathVariable String sessionId) {
        sessionService.saveSession(announcementId, sessionId);
        return Response.create(HttpStatus.ACCEPTED, "화상 오디션을 위해 생성한 세션 정보를 저장했습니다.");
    }

}
