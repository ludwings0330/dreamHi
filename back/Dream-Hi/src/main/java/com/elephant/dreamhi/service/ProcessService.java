package com.elephant.dreamhi.service;

import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.FileDto;
import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.dto.ProcessStageDto;
import com.elephant.dreamhi.security.PrincipalDetails;
import java.util.List;

public interface ProcessService {

    ProcessStageDto findProcessAndStage(Long announcementId, PrincipalDetails user);

    void saveProcessWithRecruiting(Long announcementId);

    Long saveProcessWithoutRecruiting(ProcessSaveDto processSaveDto);

    void saveAllNoticeFiles(Long processId, List<FileDto> fileDtos) throws NotFoundException;

}
