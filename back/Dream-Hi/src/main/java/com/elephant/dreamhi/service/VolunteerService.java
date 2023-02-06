package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.VolunteerApplyRequestDto;
import com.elephant.dreamhi.model.dto.VolunteerManageRequestDto;
import com.elephant.dreamhi.model.dto.VolunteerSearchCondition;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto.VolunteerSimpleInfo;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Casting;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.VolunteerState;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.CastingRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.UserRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolunteerService {

    private final AuthService authService;
    private final VolunteerRepository volunteerRepository;
    private final AnnouncementRepository announcementRepository;
    private final CastingRepository castingRepository;
    private final UserRepository userRepository;
    private final ProcessRepository processRepository;

    @Transactional
    public void userApplyOnAnnouncement(VolunteerApplyRequestDto requestDto) throws NotFoundException, IllegalArgumentException {
        log.info("공고지원 시작 : {}", requestDto);
        if (authService.isAnonymous(requestDto.getUserId())) {
            throw new AccessDeniedException("익명 사용자는 지원할 수 없습니다.");
        }
        // 유저 검증 (이미 완료되어있는데 또 확인해야하나?)
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new NotFoundException("유저가 존재하지 않습니다."));

        // 현재 서류 진행 중, 뭐 진행중 이런거는 state로 관리하면 좋을 듯
        Announcement announcement = announcementRepository.findById(requestDto.getAnnouncementId())
                                                          .orElseThrow(() -> new NotFoundException("존재하지 않는 공고입니다."));

        Process process = processRepository.findLastProcessByAnnouncementId(announcement.getId()).orElseThrow();
        if (!process.getState().equals(ProcessState.RECRUITING)) {
            throw new IllegalArgumentException("서류 지원 기간이 아닌 공고입니다.");
        }

        // 캐스팅을 돌면서 volunteer 엔티티를 만들고 저장한다. (한번에)
        List<Volunteer> volunteers = new ArrayList<>();
        for (Long castingId :
                requestDto.getCastingIds()) {
            Casting casting = castingRepository.findById(castingId)
                                               .orElseThrow(() -> new NotFoundException("존재하지 않는 배역이 포함되어있습니다."));

            // 이미 지원한 배역에 대한 처리가 필요하다.
            volunteers.add(Volunteer.builder().announcement(announcement).casting(casting).user(user).process(process)
                                    .state(VolunteerState.NONE).build());
        }

        volunteerRepository.saveAll(volunteers);
    }

    @Transactional
    public void manageVolunteer(VolunteerManageRequestDto requestDto) throws NotFoundException {
        // volunteerId 존재여부 확인
        Volunteer volunteer = volunteerRepository.findById(requestDto.getVolunteerId())
                                                 .orElseThrow(() -> new NotFoundException("지원자가 존재하지 않습니다."));

        // 상태 업데이트
        // setter 이름을 뭘로해야 합리적일까? reflectProcessResult? 일단 setter로 두자
        volunteer.setState(requestDto.getState());
    }

    public void findVolunteersByCondition(VolunteerSearchCondition condition) {
        // 존재하는 공고인지 확인
        //
    }

    public VolunteerSearchResponseDto findVolunteersByCastingIdAndCondition(VolunteerSearchCondition condition) {
//        announcementId, castingId, state
        VolunteerSearchResponseDto responseDto = new VolunteerSearchResponseDto();

        // 현재 공고의 프로세스 id 구함
        Process process = processRepository.findLastProcessByAnnouncementId(condition.getAnnouncementId()).orElseThrow();
        condition.setProcessId(process.getId());
        // 만약 이미 끝난 프로세스면 불가능

        // 배역이름 구하기
        responseDto.setCastingId(condition.getCastingId());
        Casting casting = castingRepository.findById(condition.getCastingId()).orElseThrow(() -> new NotFoundException("존재하지 않는 배역입니다."));
        responseDto.setCastingName(casting.getName());

        // 해당 배역에 지원한 유저들 모두 구하기 (필터링 -> state)
        final Page<VolunteerSimpleInfo> volunteersByCondition = volunteerRepository.findVolunteersByCondition(condition);
        responseDto.setVolunteers(volunteersByCondition);

        // 해당 castingId의 보류, 합격, 미결정, 탈락자의 숫자를 구한다
        Map<VolunteerState, Long> stateSummary = volunteerRepository.getVolunteerStateSummary(condition);
        responseDto.setStateSummary(stateSummary);

        volunteerRepository.getVolunteersByCondition(condition);

        return responseDto;
    }

    public List<VolunteerSearchResponseDto> findAllVolunteers(VolunteerSearchCondition condition) {
        List<VolunteerSearchResponseDto> result = new ArrayList<>();
        List<Long> castingIds = castingRepository.findIdByAnnouncementId(condition.getAnnouncementId());

        for (Long castingId :
                castingIds) {
            condition.setCastingId(castingId);
            result.add(findVolunteersByCastingIdAndCondition(condition));
        }

        return result;
    }

}
