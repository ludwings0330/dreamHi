package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementNameDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.entity.Announcement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface AnnouncementRepositoryCustom {

    Optional<Announcement> findByAnnouncementId(Long announcementId);

    long findTotalCountByCondition(AnnouncementSearchCondition condition, Long userId);

    List<Long> findAnnouncementIdsByCondition(AnnouncementSearchCondition condition, Pageable pageable, Long userId);

    List<AnnouncementSimpleDto> findAllByIds(List<Long> announcementIds, Boolean isFollow);

    List<AnnouncementNameDto> findWeeklyAnnouncements(LocalDate endDate);

    Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId);

    List<AnnouncementNameDto> findTopAnnouncementsWithRecruiting(final int N);

}
