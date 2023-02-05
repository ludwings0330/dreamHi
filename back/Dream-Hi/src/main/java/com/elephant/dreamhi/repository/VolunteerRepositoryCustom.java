package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.VolunteerSearchCondition;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.VolunteerState;
import java.util.List;
import java.util.Map;

public interface VolunteerRepositoryCustom {

    List<Volunteer> findByUserIdAndAnnouncementId(Long userId, Long announcementId);

    Map<VolunteerState, Long> getVolunteerStateSummary(VolunteerSearchCondition condition);

    VolunteerSearchResponseDto getVolunteersByCondition(VolunteerSearchCondition condition);

}
