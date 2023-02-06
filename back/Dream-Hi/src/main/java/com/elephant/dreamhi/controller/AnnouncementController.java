package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSaveDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.AnnouncementUpdateDto;
import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.AnnouncementService;
import com.elephant.dreamhi.service.CastingService;
import com.elephant.dreamhi.service.ProcessService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@Slf4j
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final CastingService castingService;
    private final ProcessService processService;

    /**
     * @param searchCondition 필터, 검색어 등의 검색 조건
     * @param pageable        페이지네이션을 위한 정보
     * @param user            현재 유저
     * @return 페이지네이션이 완료된 공고 목록에서의 정보를 담은 DTO 목록을 Body에 담아서 반환
     */
    @GetMapping
    public ResponseEntity<Body> findList(
            @ModelAttribute AnnouncementSearchCondition searchCondition,
            @PageableDefault(size = 4) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        Page<AnnouncementSimpleDto> announcementSimpleDtos = announcementService.findList(searchCondition, pageable, user);

        if (announcementSimpleDtos.isEmpty()) {
            return Response.create(HttpStatus.NO_CONTENT, "NO_CONTENT");
        }

        return Response.create(HttpStatus.OK, "OK", announcementSimpleDtos);
    }

    /**
     * @param announcementId 공고 ID
     * @param user           시큐리티 컨텍스트에 저장된 인증 객체로부터 얻어낸 Principal
     * @return 공고 상세 정보를 응답으로 반환
     * @throws NotFoundException 공고 ID로 공고를 검색했는데 동시성 문제 등의 이슈로 공고를 찾을 수 없게 된 상황에 예외를 던진다.
     */
    @GetMapping("/{announcementId}")
    public ResponseEntity<Body> findDetail(@PathVariable Long announcementId, @AuthenticationPrincipal PrincipalDetails user)
            throws NotFoundException {
        AnnouncementDetailDto announcementDetailDto = announcementService.findDetail(announcementId, user);
        return Response.create(HttpStatus.OK, "OK", announcementDetailDto);
    }

    /**
     * 공고 상세 페이지에서 해당 공고의 배역 상세 정보를 조회한다.
     *
     * @param annoucementId 공고 ID
     * @return Body에 공고의 배역 상세 정보를 담아서 반환, 없을 때는 Body에 아무것도 담지 않는다.
     */
    @GetMapping("/{annoucementId}/castings")
    public ResponseEntity<Body> findCastings(@PathVariable Long annoucementId) {
        List<CastingDetailDto> castingDetailDtos = castingService.findCastingDetails(annoucementId);

        if (castingDetailDtos.isEmpty()) {
            return Response.create(HttpStatus.NO_CONTENT, "NO_CONTENT");
        }

        return Response.create(HttpStatus.OK, "OK", castingDetailDtos);
    }

    /**
     * 공고의 진행 상태를 유저별로 다르게 표현하기 위해 Process와 Volunteer를 조회한다.
     *
     * @param announcementId 공고ID
     * @param user           현재 로그인한 유저 또는 게스트
     * @return 공고의 process와 유저의 현재 Stage를 저장한 객체를 Body에 담아서 반환
     */
    @GetMapping("/{announcementId}/process")
    public ResponseEntity<Body> findProcessAndStage(@PathVariable Long announcementId, @AuthenticationPrincipal PrincipalDetails user) {
        ProcessStageDto userStageDto = processService.findProcessAndStage(announcementId, user);
        return Response.create(HttpStatus.OK, "OK", userStageDto);
    }

    /**
     * @param announcementSaveDto 공고를 저장할 때 필요한 데이터를 Request Body로 입력받는다.
     * @param user                현재 로그인한 유저
     * @return 저장에 성공한 경우 201, CREATED 반환
     * @throws NotFoundException 저장하려는 배역의 스타일이 DB에 존재하지 않는 경우 발생하는 예외
     */
    @PostMapping
    @PreAuthorize("@checker.hasEditorAuthority(#user, #announcementSaveDto.producerId)")
    public ResponseEntity<Body> saveAnnouncement(
            @RequestBody @Valid AnnouncementSaveDto announcementSaveDto,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        announcementService.saveAnnouncement(announcementSaveDto);
        return Response.create(HttpStatus.CREATED, "CREATED");
    }

    @PutMapping
    @PreAuthorize("@checker.hasEditorAuthority(#user, #announcementUpdateDto.producerId)")
    public ResponseEntity<Body> updateAnnouncement(
            @RequestBody @Valid AnnouncementUpdateDto announcementUpdateDto,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        announcementService.updateAnnouncement(announcementUpdateDto);
        return Response.create(HttpStatus.ACCEPTED, "ACCEPTED");
    }

    @DeleteMapping("/{producerId}/{announcementId}")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> deleteAnnouncement(
            @PathVariable Long producerId,
            @PathVariable Long announcementId,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        announcementService.deleteAnnouncement(announcementId);
        return Response.create(HttpStatus.ACCEPTED, "ACCEPTED");
    }

}
