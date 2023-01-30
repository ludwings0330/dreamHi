package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import java.util.Optional;

public interface AnnouncementRepositoryCustom {

    Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId);

}
