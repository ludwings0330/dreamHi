package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, AnnouncementRepositoryCustom {

    @Modifying
    @Query("UPDATE Announcement a SET a.hit = a.hit + 1 where a.id = :announcementId")
    void plusHitByAnnouncementId(Long announcementId);

}
