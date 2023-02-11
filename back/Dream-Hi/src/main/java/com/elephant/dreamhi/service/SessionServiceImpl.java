package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Session;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final AnnouncementRepository announcementRepository;
    private final SessionRepository sessionRepository;

    @Override
    @Transactional
    public void saveSession(Long announcementId, String sessionId) {
        Announcement announcement = announcementRepository.getReferenceById(announcementId);
        sessionRepository.save(new Session(announcement, sessionId));
    }

}
