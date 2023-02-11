package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Session;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s "
            + "FROM Session s "
            + "JOIN s.announcement a "
            + "WHERE a.id = :announcementId ")
    Optional<Session> findLatestSessionByAnnouncementId(Long announcementId);

}
