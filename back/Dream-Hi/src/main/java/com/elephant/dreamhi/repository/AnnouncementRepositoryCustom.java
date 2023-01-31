package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.entity.Announcement;
import java.util.Optional;

public interface AnnouncementRepositoryCustom {

    Optional<Announcement> findAnnouncementById(Long id);

    Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId);

}
