package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementNameDto;
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
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
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
            return Response.create(HttpStatus.NO_CONTENT, "조회된 공고가 없습니다.");
        }

        return Response.create(HttpStatus.OK, "공고 목록을 조회했습니다.", announcementSimpleDtos);
    }

    /**
     * @param pageable 2개의 공고를 조회하는 설정을 기본으로 하는 페이지네이션을 위한 정보
     * @param user     현재 로그인한 유저
     * @return 팔로우한 공고 중 가장 최근에 생성된 2개의 공고를 Response의 Body에 담아서 반환한다.
     */
    @GetMapping("/my/follow")
    @PreAuthorize("@checker.isLoginUser(#user)")
    public ResponseEntity<Body> findFollowList(
            @PageableDefault(size = 2) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        AnnouncementSearchCondition condition = AnnouncementSearchCondition.builder()
                                                                           .isFollow(Boolean.TRUE)
                                                                           .build();

        Page<AnnouncementSimpleDto> announcementSimpleDtos = announcementService.findList(condition, pageable, user);

        if (announcementSimpleDtos.isEmpty()) {
            return Response.noContent();
        }

        return Response.create(HttpStatus.OK, "2개의 팔로우한 공고를 조회했습니다.", announcementSimpleDtos);
    }

    /**
     * @param pageable 2개의 공고를 조회하는 설정을 기본으로 하는 페이지네이션을 위한 정보
     * @param user
     * @return 지원한 공고 중 가장 최근에 생성된 2개의 공고를 Response의 Body에 담아서 반환한다.
     */
    @GetMapping("/my/volunteer")
    @PreAuthorize("@checker.isLoginUser(#user)")
    public ResponseEntity<Body> findVolunteerList(
            @PageableDefault(size = 2) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        AnnouncementSearchCondition condition = AnnouncementSearchCondition.builder()
                                                                           .isVolunteer(Boolean.TRUE)
                                                                           .build();

        Page<AnnouncementSimpleDto> announcementSimpleDtos = announcementService.findList(condition, pageable, user);

        if (announcementSimpleDtos.isEmpty()) {
            return Response.noContent();
        }

        return Response.create(HttpStatus.OK, "2개의 지원한 공고를 조회했습니다.", announcementSimpleDtos);
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
        return Response.create(HttpStatus.OK, "공고 상세 정보를 조회했습니다.", announcementDetailDto);
    }

    /**
     * @return 주간 공고 목록을 조회하여, 월요일부터 일요일까지 순차적으로 최대 2개의 공고를 Response의 Body에 담아서 반환한다.
     */
    @GetMapping("/weekly")
    public ResponseEntity<Body> findWeeklyAnnouncements() {
        Map<DayOfWeek, List<AnnouncementNameDto>> weeklyDtos = announcementService.findWeeklyAnnouncements();
        return Response.create(HttpStatus.OK, "주간 공고 목록을 조회했습니다.", weeklyDtos);
    }

    /**
     * @param N 조회수 순으로 상위 몇 개의 공고를 조회할지 결정한다.
     * @return 모집 중인 공고 중 조회수 순으로 상위 N개의 공고를 조회하여 Response의 Body에 담아 반환한다.
     */
    @GetMapping("/top/{N}")
    public ResponseEntity<Body> findTopAnnouncementsWithRecruiting(@PathVariable final int N) {
        List<AnnouncementNameDto> announcementNameDtos = announcementService.findTopAnnouncementsWithRecruiting(N);

        if (announcementNameDtos.isEmpty()) {
            return Response.noContent();
        }

        return Response.create(HttpStatus.OK, "Top N 공고 목록을 조회했습니다.", announcementNameDtos);
    }

    /**
     * 공고 상세 페이지에서 해당 공고의 배역 상세 정보를 조회한다.
     *
     * @param announcementId 공고 ID
     * @return Body에 공고의 배역 상세 정보를 담아서 반환, 없을 때는 Body에 아무것도 담지 않는다.
     */
    @GetMapping("/{announcementId}/castings")
    public ResponseEntity<Body> findCastings(@PathVariable Long announcementId) {
        List<CastingDetailDto> castingDetailDtos = castingService.findCastingDetails(announcementId);

        if (castingDetailDtos.isEmpty()) {
            return Response.create(HttpStatus.NO_CONTENT, "공고에 등록된 배역이 없습니다. 이런 일은 일어나서는 안 됩니다.");
        }

        return Response.create(HttpStatus.OK, "공고에 등록된 배역을 조회했습니다.", castingDetailDtos);
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
        return Response.create(HttpStatus.OK, "회원별 공고 상태를 조회했습니다.", userStageDto);
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
        return Response.create(HttpStatus.CREATED, "공고를 등록했습니다.");
    }

    @PutMapping
    @PreAuthorize("@checker.hasEditorAuthority(#user, #announcementUpdateDto.producerId)")
    public ResponseEntity<Body> updateAnnouncement(
            @RequestBody @Valid AnnouncementUpdateDto announcementUpdateDto,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        announcementService.updateAnnouncement(announcementUpdateDto);
        return Response.create(HttpStatus.ACCEPTED, "공고를 수정했습니다.");
    }

    @DeleteMapping("/{producerId}/{announcementId}")
    @PreAuthorize("@checker.hasEditorAuthority(#user, #producerId)")
    public ResponseEntity<Body> deleteAnnouncement(
            @PathVariable Long producerId,
            @PathVariable Long announcementId,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        announcementService.deleteAnnouncement(announcementId);
        return Response.create(HttpStatus.ACCEPTED, "공고를 삭제했습니다. 이제 돌이킬 수 없습니다.");
    }

}
