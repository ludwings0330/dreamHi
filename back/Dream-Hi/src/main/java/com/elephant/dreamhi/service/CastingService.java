package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.CastingDetailDto;
import java.util.List;

public interface CastingService {

    List<CastingDetailDto> findCastingDetails(Long announcementId);

}
