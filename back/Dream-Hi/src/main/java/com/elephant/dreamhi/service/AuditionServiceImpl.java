package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Session;
import com.elephant.dreamhi.model.statics.StageName;
import com.elephant.dreamhi.repository.ProcessRepository;
import com.elephant.dreamhi.repository.SessionRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

    private final ProcessRepository processRepository;
    private final SessionRepository sessionRepository;

    @Override
    public String findFileUrl(Long processId) {
        return sessionRepository.findByProcessId(processId)
                                .orElseThrow(() -> new NotFoundException("화상 오디션에 대한 세션을 찾을 수 없습니다."))
                                .getFileUrl();
    }

    @Override
    public String findSessionId(Long processId) throws NotFoundException {
        return sessionRepository.findByProcessId(processId)
                                .orElseThrow(() -> new NotFoundException("화상 오디션에 대한 세션을 찾을 수 없습니다."))
                                .getSessionId();
    }

    @Override
    @Transactional
    public void saveSession(Long processId, String fileUrl) throws NotFoundException, IllegalArgumentException {
        Process process = processRepository.findById(processId)
                                           .orElseThrow(() -> new NotFoundException("현재 오디션의 절차를 찾을 수 없습니다."));

        if (process.getStage() != StageName.VIDEO) {
            throw new IllegalArgumentException("현재 오디션의 절차가 화상 오디션이 아닙니다. 세션을 생성할 수 없습니다.");
        }

        String uniqueSessionId = UUID.randomUUID().toString();
        sessionRepository.save(new Session(process, uniqueSessionId, fileUrl));
    }

}
