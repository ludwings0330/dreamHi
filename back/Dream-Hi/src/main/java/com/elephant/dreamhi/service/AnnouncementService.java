package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.security.PrincipalDetails;

public interface AnnouncementService {

    AnnouncementDetailDto findDetail(Long id, PrincipalDetails user) throws NotFoundException;

}
