package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.StageName;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProcessRepositoryCustom {

    Optional<Process> findLastProcessByAnnouncementId(Long announcementId);

    Optional<Process> findByIdAndStageAndState(Long processId, StageName stageName, ProcessState processState);

    Map<Long, Process> findLastProcessesByAnnouncementIds(List<Long> announcementIds);

}
