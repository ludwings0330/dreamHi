package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.model.dto.MediaFileRequestDto;
import com.elephant.dreamhi.model.dto.MediaFileResponseDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.MediaFileService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        MediaFileResponseDto responseDto = mediaFileService.findMediaFilesByActorProfileId(id);
        return Response.create(HttpStatus.OK, HttpStatus.OK.name(), responseDto);
    }

    /**
     * MediaFile 추가 메소드
     */
    @PostMapping("/api/actors/{actorId}/media")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addMediaFile(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long actorId,
                                          @RequestBody MediaFileRequestDto mediaFileRequestDto) {
        mediaFileService.addMediaFile(principalDetails.getId(), actorId, mediaFileRequestDto);
        return Response.accepted();
    }

    /**
     * MediaFile 삭제 메소드
     *
     * @param principalDetails        : 접근 중 주체
     * @param actorProfileId          : 배우 프로필 id
     * @param actorProfileMediaFileId : 미디어 파일 id
     * @return 200
     * @throws AccessDeniedException          : 사용자가 잘못된 배우 프로필 수정을 요청 시 권한 없음 발생
     * @throws EmptyResultDataAccessException : 삭제할 데이터가 없는 경우 발생
     */
    @DeleteMapping("/api/actors/{actorProfileId}/media/{actorProfileMediaFileId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Body> deleteMediaFile(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long actorProfileId,
                                                @PathVariable Long actorProfileMediaFileId)
            throws AccessDeniedException, EmptyResultDataAccessException {
        log.info("id : {} {} {}", principalDetails.getId(), actorProfileId, actorProfileMediaFileId);
        mediaFileService.deleteMediaFile(principalDetails.getId(), actorProfileId, actorProfileMediaFileId);
        return Response.ok();
    }

}
