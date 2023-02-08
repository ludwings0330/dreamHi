package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.AnnouncementNameDto;
import com.elephant.dreamhi.model.entity.Announcement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementRepositoryCustom {

    Optional<Announcement> findByAnnouncementId(Long announcementId);

    Page<AnnouncementSimpleDto> findAllByCondition(AnnouncementSearchCondition condition, Pageable pageable, Long id);

    List<AnnouncementNameDto> findWeeklyAnnouncements(LocalDate endDate);

    Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId);

    List<AnnouncementNameDto> findTopAnnouncementsWithRecruiting(final int N);

}
