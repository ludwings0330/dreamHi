package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Volunteer;
import java.util.List;

public interface VolunteerRepositoryCustom {

    List<Volunteer> findByUserIdAndAnnouncementId(Long userId, Long announcementId);

}
