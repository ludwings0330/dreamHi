package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.AnnouncementSaveDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {

    AnnouncementDetailDto findDetail(Long id, PrincipalDetails user) throws NotFoundException;

    Page<AnnouncementSimpleDto> findList(AnnouncementSearchCondition searchCondition, Pageable pageable, PrincipalDetails user);

    void saveAnnouncementDetail(AnnouncementSaveDto announcementSaveDto) throws NotFoundException;

}
