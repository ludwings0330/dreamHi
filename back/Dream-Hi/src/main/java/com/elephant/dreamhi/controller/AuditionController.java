package com.elephant.dreamhi.controller;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookRequestDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import com.elephant.dreamhi.model.dto.BookedVolunteerDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import com.elephant.dreamhi.service.AuditionService;
import com.elephant.dreamhi.utils.Response;
import com.elephant.dreamhi.utils.Response.Body;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements/{announcementId}/audition")
@RequiredArgsConstructor
public class AuditionController {

    private final AuditionService auditionService;

    /**
     * @param processId      오디션의 현재 절차 ID
     * @param announcementId 현재 공고 ID
     * @param user           현재 로그인 한 유저
     * @return 이미 예약한 지원자의 경우 예약 일자에 대한 정보를 Response의 Body에 담아서 반환
     * @throws NotFoundException 지원자가 아직 예약을 하지 않은 지원자일 때 발생하는 예외
     */
    @GetMapping("/on/{processId}/reservation")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasPassedAuthority(#user, #announcementId, #processId)")
    public ResponseEntity<Body> isBookedVolunteer(
            @PathVariable Long processId,
            @PathVariable Long announcementId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        BookResponseDto bookResponseDto = auditionService.findBookOfVolunteer(processId, user);
        return Response.create(HttpStatus.OK, "이미 예약한 지원자입니다. 예약 일자를 확인해주세요.", bookResponseDto);
    }

    /**
     * @param processId      오디션의 현재 절차 ID
     * @param announcementId 현재 공고 ID
     * @param producerId     현재 공고를 작성한 제작사 ID
     * @param user           현재 로그인 한 유저
     * @return 오늘 예약된 화상 오디션 지원자가 있으면 '200 OK'와 지원자의 목록을 Response의 Body에 담아서 반환한다. 없으면 '204 NO_CONTENT'를 전달한다.
     */
    @GetMapping("/on/{processId}/volunteers/today")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasAnnouncementAuthority(#user, #producerId, #announcementId)")
    public ResponseEntity<Body> findBookedVolunteersOnToday(
            @PathVariable Long processId,
            @PathVariable Long announcementId,
            @RequestParam(name = "pid") Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        List<BookedVolunteerDto> bookedVolunteerDtos = auditionService.findBookedVolunteersOnToday(processId);

        if (bookedVolunteerDtos.isEmpty()) {
            return Response.noContent();
        }

        return Response.create(HttpStatus.OK, "금일 예약된 화상 오디션 지원자를 조회했습니다.", bookedVolunteerDtos);
    }

    /**
     * @param processId      오디션의 현재 절차 ID
     * @param announcementId 현재 공고 ID
     * @param producerId     현재 공고를 작성한 제작사 ID, 현재 유저가 지원자인 경우 null을 받아야 한다.
     * @param user           현재 로그인 한 유저
     * @return 예약 가능한 날짜의 시작일과 종료일을 Response의 Body에 담아서 반환
     * @throws NotFoundException 제작사에서 화상 오디션 예약 가능한 일자를 등록하지 않은 경우에 발생하는 예외
     */
    @GetMapping("/on/{processId}/period")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasPassedAuthority(#user, #producerId, #announcementId, #processId)")
    public ResponseEntity<Body> findBookPeriod(
            @PathVariable Long processId,
            @PathVariable Long announcementId,
            @RequestParam(name = "pid", required = false) Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        BookPeriodDto bookPeriodDto = auditionService.findBookPeriod(processId);
        return Response.create(HttpStatus.OK, "예약 가능한 날짜의 시작일과 종료일을 조회했습니다.", bookPeriodDto);
    }

    /**
     * @param processId      오디션의 현재 절차 ID
     * @param announcementId 현재 공고 ID
     * @param producerId     현재 공고를 작성한 제작사 ID, 현재 유저가 지원자인 경우 null을 받아야 한다.
     * @param date           선택한 예약 날짜
     * @param user           현재 로그인 한 유저
     * @return 지원자에게는 예약 현황을, 제작사에게는 각 예약 현황에 지원한 User의 ID를 Response의 Body에 담아서 반환
     */
    @GetMapping("/on/{processId}/schedules")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasPassedAuthority(#user, #producerId, #announcementId, #processId)")
    public ResponseEntity<Body> findAllBook(
            @PathVariable Long processId,
            @PathVariable Long announcementId,
            @RequestParam(name = "pid", required = false) Long producerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @AuthenticationPrincipal PrincipalDetails user
    ) {
        Object bookDtos;

        if (producerId == null) {
            bookDtos = auditionService.findAllBookForVolunteer(processId, date);
        } else {
            bookDtos = auditionService.findAllBookForProducer(processId, date);
        }

        return Response.create(HttpStatus.OK, "요청한 일자의 예약 정보를 조회했습니다.", bookDtos);
    }

    /**
     * @param processId      오디션의 현재 절차 ID
     * @param announcementId 현재 공고 ID
     * @param producerId     현재 공고를 작성한 제작사 ID, 현재 유저가 지원자인 경우 null을 받아야 한다.
     * @param user           현재 로그인 한 유저
     * @return 화상 오디션을 위한 파일 URL 조회 결과를 Response의 Body에 담아서 반환
     * @throws NotFoundException 현재 절차 ID에 대하여 사전에 저장해 둔 세션 정보가 없을 때 발생하는 예외
     */
    @GetMapping("/on/{processId}/file")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasPassedAuthority(#user, #producerId, #announcementId, #processId)")
    public ResponseEntity<Body> findFileUrl(
            @PathVariable Long processId,
            @PathVariable Long announcementId,
            @RequestParam(name = "pid", required = false) Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        String fileUrl = auditionService.findFileUrl(processId);
        return Response.create(HttpStatus.OK, "화상 오디션을 위한 파일 URL를 조회했습니다.", fileUrl);
    }

    /**
     * @param processId 오디션의 현재 절차 ID
     * @param now       사용자의 현재 시간
     * @param user      현재 로그인한 유저
     * @return 화상 오디션에 사용할 세션 ID를 Response의 Body에 담아서 반환
     * @throws NotFoundException 현재 절차 ID에 대하여 사전에 저장해 둔 세션 정보가 없을 때 발생하는 예외
     */
    @GetMapping("/on/{processId}/session")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasBookAuthority(#user, #producerId, #announcementId, #processId, #now)")
    public ResponseEntity<Body> findSessionId(
            @PathVariable Long processId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime now,
            @PathVariable Long announcementId,
            @RequestParam(required = false) Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException {
        String sessionId = auditionService.findSessionId(processId);
        return Response.create(HttpStatus.OK, "화상 오디션 입장을 위한 세션 ID를 조회했습니다.", sessionId);
    }

    /**
     * @param processId      오디션의 현재 절차 ID, stage가 화상 오디션이어야 한다.
     * @param fileUrl        화상 오디션을 위한 참고 파일을 저장해 둔 URL
     * @param announcementId 현재 공고 ID
     * @param producerId     현재 공고를 작성한 제작사 ID
     * @param user           현재 로그인 한 유저
     * @return 화상 오디션에 대한 세션 정보를 저장하는 데 성공한 경우
     * @throws NotFoundException        오디션의 현재 절차를 조회할 수 없을 때 발생하는 예외
     * @throws IllegalArgumentException 현재 절차의 stage가 화상 오디션이 아닐 때 발생하는 예외
     */
    @PostMapping("/on/{processId}")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasAnnouncementAuthority(#user, #producerId, #announcementId)")
    public ResponseEntity<Body> saveSession(
            @PathVariable Long processId,
            @RequestBody String fileUrl,
            @PathVariable Long announcementId,
            @RequestBody Long producerId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException, IllegalArgumentException {
        auditionService.saveSession(processId, fileUrl);
        return Response.create(HttpStatus.ACCEPTED, "화상 오디션을 위한 세션 정보를 저장했습니다.");
    }

    /**
     * 프론트에서 넘겨주는 데이터의 한계로 한 명의 유저가 한 개의 배역에만 지원했다고 가정했습니다. 추후에 수정한다면 프론트에서 배역별로 일정을 받아야 하고, 배역별로 권한을 확인하는 방향으로 수정해야 합니다.
     *
     * @param processId      오디션의 현재 절차 ID, stage가 화상 오디션이어야 한다.
     * @param bookRequestDto 오디션 시작 시간과 종료 시간을 전달하는 DTO
     * @param announcementId 현재 공고 ID
     * @param user           현재 로그인 한 유저
     * @return 화상 오디션 예약에 성공한 경우 '201 CREATED' 반환
     * @throws NotFoundException        오디션의 현재 절차를 조회할 수 없을 때 발생하는 예외
     * @throws IllegalArgumentException 현재 절차의 stage가 화상 오디션이 아닐 때 발생하는 예외
     */
    @PostMapping("/on/{processId}/book")
    @PreAuthorize("@checker.isLoginUser(#user) && @checker.hasPassedAuthority(#user, #announcementId, #processId)")
    public ResponseEntity<Body> saveBookOfVolunteer(
            @PathVariable Long processId,
            @RequestBody BookRequestDto bookRequestDto,
            @PathVariable Long announcementId,
            @AuthenticationPrincipal PrincipalDetails user
    ) throws NotFoundException, IllegalArgumentException {
        auditionService.saveBookOfVolunteer(processId, bookRequestDto, user);
        return Response.create(HttpStatus.CREATED, "지원자의 화상 오디션 일정을 예약했습니다.");
    }

}
