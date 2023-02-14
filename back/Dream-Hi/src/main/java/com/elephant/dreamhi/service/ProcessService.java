package com.elephant.dreamhi.service;

import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.List;
import java.util.Map;

public interface ProcessService {

    Map<Long, ProcessStageDto> findProcessAndStages(List<Long> announcementIds, PrincipalDetails user);

    ProcessStageDto findProcessAndStage(Long announcementId, PrincipalDetails user);

    void saveProcessWithRecruiting(Long announcementId);

    Long saveProcessWithoutRecruiting(ProcessSaveDto processSaveDto);

}
