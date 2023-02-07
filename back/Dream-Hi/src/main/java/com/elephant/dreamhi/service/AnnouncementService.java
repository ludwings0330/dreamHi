package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSaveDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.AnnouncementUpdateDto;
import com.elephant.dreamhi.model.dto.AnnouncementWeeklyDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {

    AnnouncementDetailDto findDetail(Long id, PrincipalDetails user) throws NotFoundException;

    Page<AnnouncementSimpleDto> findList(AnnouncementSearchCondition searchCondition, Pageable pageable, PrincipalDetails user);

    void saveAnnouncement(AnnouncementSaveDto announcementSaveDto) throws NotFoundException;

    void updateAnnouncement(AnnouncementUpdateDto announcementUpdateDto) throws NotFoundException;

    void deleteAnnouncement(Long announcementId);

    Map<DayOfWeek, List<AnnouncementWeeklyDto>> findWeeklyAnnouncements();

}
