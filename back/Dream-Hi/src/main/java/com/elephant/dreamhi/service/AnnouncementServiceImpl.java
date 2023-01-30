package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public Optional<AnnouncementDetailDto> findDetail(Long announcementId, PrincipalDetails user) {
        if (!user.getId().equals(0L)) {
            return announcementRepository.findByAnnouncementIdAndFollowerId(announcementId, user.getId());
        }

        return announcementRepository.findById(announcementId).map(Announcement::toAnnouncementDetailDto); // fetch join 구현해야 함
    }

}