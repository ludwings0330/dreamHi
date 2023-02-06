package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.CastingDetailDto;
import java.util.List;

public interface CastingRepositoryCustom {

    List<CastingDetailDto> findByAnnouncementId(Long announcementId);

    List<Long> findIdByAnnouncementId(Long announcementId);

}
