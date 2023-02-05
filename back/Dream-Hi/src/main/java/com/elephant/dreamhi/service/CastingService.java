package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.dto.CastingSaveDto;
import com.elephant.dreamhi.model.dto.CastingUpdateDto;
import com.elephant.dreamhi.model.entity.Announcement;
import java.util.List;

public interface CastingService {

    List<CastingDetailDto> findCastingDetails(Long announcementId);

    void saveCasting(Announcement announcement, CastingSaveDto castingSaveDto) throws NotFoundException;

    void updateCasting(Announcement announcement, CastingUpdateDto castingUpdateDto) throws NotFoundException;

}
