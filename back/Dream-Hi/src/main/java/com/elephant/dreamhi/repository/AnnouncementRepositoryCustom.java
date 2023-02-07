package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.entity.Announcement;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementRepositoryCustom {

    Optional<Announcement> findByAnnouncementId(Long announcementId);

    Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId);

    Page<AnnouncementSimpleDto> findAllByCondition(AnnouncementSearchCondition condition, Pageable pageable, Long id);

}
