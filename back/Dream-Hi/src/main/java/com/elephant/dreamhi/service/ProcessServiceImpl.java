package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.UserStageName;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProcessServiceImpl implements ProcessService {

    private final AnnouncementRepository announcementRepository;
    private final ProcessRepository processRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public Map<Long, ProcessStageDto> findProcessAndStages(List<Long> announcementIds, PrincipalDetails user) {
        Map<Long, Process> lastProcessByAnnouncementId = processRepository.findLastProcessesByAnnouncementIds(announcementIds);

        if (user.isGuest()) {
            return lastProcessByAnnouncementId.entrySet()
                                              .stream()
                                              .collect(Collectors.toMap(
                                                      Entry::getKey,
                                                      e -> new ProcessStageDto(e.getValue(), UserStageName.NONE)
                                              ));
        }

        Map<Long, List<Volunteer>> volunteersByAnnouncementId = volunteerRepository.findAllByUserIdAndAnnouncementIds(user.getId(), announcementIds);
        return volunteersByAnnouncementId.entrySet()
                                         .stream()
                                         .collect(Collectors.toMap(
                                                 Entry::getKey,
                                                 e -> ProcessStageDto.toDto(
                                                         lastProcessByAnnouncementId.get(e.getKey()), e.getValue()
                                                 )
                                         ));
    }

    /**
     * @param announcementId 현재 공고의 ID
     * @param user           현재 로그인한 유저
     * @return 현재 공고에서 유저의 상태를 반환
     */
    @Override
    public ProcessStageDto findProcessAndStage(Long announcementId, PrincipalDetails user) {
        Process lastProcess = processRepository.findLastProcessByAnnouncementId(announcementId)
                                               .orElseThrow(() -> new IllegalArgumentException("공고의 진행 상황을 찾을 수 없습니다. 유효하지 않은 공고ID 입니다."));

        if (user.isGuest()) {
            return new ProcessStageDto(lastProcess, UserStageName.NONE);
        }

        List<Volunteer> volunteers = volunteerRepository.findAllByUserIdAndAnnouncementId(user.getId(), announcementId);
        return ProcessStageDto.toDto(lastProcess, volunteers);
    }

    /**
     * 진행 중 절차를 공고와 연결하여 등록하고, 공고에 지원한 지원자의 절차를 갱신한다.
     *
     * @param announcementId 현재 공고의 ID
     */
    @Override
    @Transactional
    public void saveProcessWithRecruiting(Long announcementId) {
        Announcement announcement = announcementRepository.getReferenceById(announcementId);
        Process process = Process.getInstanceForInProgress(announcement);
        processRepository.save(process);
        volunteerRepository.updateAll(announcementId, process);
    }

    /**
     * 새로운 절차와 오디션 단계, 공고ID를 이용하여 절차를 저장하고, 공고의 모든 지원자의 절차를 갱신한다.
     *
     * @param processSaveDto 공고ID, 새로운 절차, 새로운 오디션 단계
     */
    @Override
    @Transactional
    public Long saveProcessWithoutRecruiting(ProcessSaveDto processSaveDto) {
//        Announcement announcement = announcementRepository.findByAnnouncementId(processSaveDto.getAnnouncementId())
//                                                          .orElseThrow(() -> new NotFoundException("[ProcessServiceImpl.saveProcess] 해당 공고를 찾을 수 없습니다."));

        Announcement announcement = announcementRepository.getReferenceById(processSaveDto.getAnnouncementId());
        Process process = Process.toEntity(announcement, processSaveDto);
        Long processId = processRepository.save(process).getId();

        // 모집 완료인 경우
//        if (process.getState() == ProcessState.FINISH) {
//            announcement.getCastings().forEach(casting -> {
//                int volunteerCount = volunteerRepository.countPassVolunteersByCastingId(casting.getId()).intValue();
//
//                if (volunteerCount > casting.getHeadcount()) {
//                    throw new IllegalStateException("[ProcessServiceImpl.saveProcess] 배역에 지정한 캐스팅 인원보다 더 많은 인원을 채용하고 있습니다.");
//                }
//            });
//        }

        volunteerRepository.updatePassVolunteers(announcement.getId(), process);

        return processId;
    }

}