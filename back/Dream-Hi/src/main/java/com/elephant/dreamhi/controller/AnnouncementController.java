package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
     * 공고 ID를 이용하여 해당 공고의 상세 정보를 응답으로 반환한다.
     * @param id 공고 ID
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findDetail(@PathVariable Long id) {
        return null;
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
