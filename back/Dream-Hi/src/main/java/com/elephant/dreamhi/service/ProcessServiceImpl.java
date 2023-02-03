package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.UserStageName;
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

}