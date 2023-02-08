package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long>, ProcessRepositoryCustom {

    @Modifying
    @Query("DELETE Process p WHERE p.announcement.id = :announcementId")
    void deleteAllByAnnouncementId(Long announcementId);

}
