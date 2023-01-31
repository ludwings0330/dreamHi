package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, AnnouncementRepositoryCustom {

}
