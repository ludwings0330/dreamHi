package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Process;
import java.util.Optional;

public interface ProcessRepositoryCustom {

    Optional<Process> findLastProcessByAnnouncementId(Long announcementId);

}
