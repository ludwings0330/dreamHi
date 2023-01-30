package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.Optional;

public interface AnnouncementService {

    Optional<AnnouncementDetailDto> findDetail(Long id, PrincipalDetails user);

}
