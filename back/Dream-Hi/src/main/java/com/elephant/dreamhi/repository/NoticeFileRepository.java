package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.NoticeFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {

    List<NoticeFile> findByProcessId(Long processId);

}
