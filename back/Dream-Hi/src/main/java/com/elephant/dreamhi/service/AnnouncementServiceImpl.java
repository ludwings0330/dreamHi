package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.repository.AnnouncementRepository;
import com.elephant.dreamhi.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementDetailDto findDetail(Long announcementId, PrincipalDetails user) throws NotFoundException {
        return announcementRepository.findByAnnouncementIdAndFollowerId(announcementId, user.getId())
                                     .orElseThrow(() -> new NotFoundException("해당 공고를 찾을 수 없습니다."));
    }

}
