package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.UserStageName;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.VolunteerRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    private final AnnouncementRepository announcementRepository;
    private final ProcessRepository processRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public ProcessStageDto findProcessAndStage(Long announcementId, PrincipalDetails user) {
        Process lastProcess = processRepository.findLastProcessByAnnouncementId(announcementId)
                                               .orElseThrow(() -> new IllegalArgumentException("공고의 진행 상황을 찾을 수 없습니다. 유효하지 않은 공고ID 입니다."));

        if (user.isGuest()) {
            return new ProcessStageDto(lastProcess.getState(), UserStageName.NONE);
        }

        List<Volunteer> volunteers = volunteerRepository.findByUserIdAndAnnouncementId(user.getId(), announcementId);
        return ProcessStageDto.toDto(lastProcess, volunteers);
    }

    @Override
    public void saveProcess(ProcessSaveDto processSaveDto) {
        if (processSaveDto.getState() == ProcessState.RECRUITING) {
            throw new IllegalArgumentException("[ProcessServiceImpl.saveProcess] Process의 state가 RECRUITING인 경우는 공고 생성 시 한 번만 저장될 수 있습니다.");
        }

        Announcement announcement = announcementRepository.findByAnnouncementId(processSaveDto.getAnnouncementId())
                                                          .orElseThrow(() -> new NotFoundException("[ProcessServiceImpl.saveProcess] 해당 공고를 찾을 수 없습니다."));

        Process process = Process.toEntity(announcement, processSaveDto);
        processRepository.save(process);

        // 오디션 진행 중인 경우
        if (process.getState() == ProcessState.IN_PROGRESS) {
            volunteerRepository.updatePassVolunteers(announcement.getId(), process); // 현재 프로세스의 합격자를 다음 프로세스로 갱신
            return;
        }

        // 모집 완료인 경우
        announcement.getCastings().forEach(casting -> {
            int volunteerCount = volunteerRepository.countPassVolunteersByCastingId(casting.getId()).intValue();

            if (volunteerCount > casting.getHeadcount()) {
                throw new IllegalStateException("[ProcessServiceImpl.saveProcess] 배역에 지정한 캐스팅 인원보다 더 많은 인원을 채용하고 있습니다.");
            }
        });

        volunteerRepository.updatePassVolunteers(announcement.getId(), process); // 현재 프로세스의 합격자를 최종 합격 처리
    }

}