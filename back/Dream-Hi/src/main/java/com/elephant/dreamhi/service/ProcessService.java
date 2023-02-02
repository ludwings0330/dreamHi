package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.security.PrincipalDetails;

public interface ProcessService {

    ProcessStageDto findProcessAndStage(Long announcementId, PrincipalDetails user);

}
