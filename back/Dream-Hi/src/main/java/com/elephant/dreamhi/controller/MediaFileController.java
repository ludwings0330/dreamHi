package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.MediaFileDto;
import com.elephant.dreamhi.service.MediaFileService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
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
public class MediaFileController {

    private final MediaFileService mediaFileService;

    /**
     * MediaFile 정보 조회 메소드
     *
     * @param id : actorProfileId
     * @return MediaFileDto
     */
    @GetMapping("/api/actors/{id}/media")
    public ResponseEntity<Body> getMediaFiles(@PathVariable Long id) {
        MediaFileDto responseDto = mediaFileService.findMediaFilesByActorProfileId(id);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), responseDto);
    }

}
