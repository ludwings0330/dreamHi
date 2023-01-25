package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorProfileMediaFileRepository extends JpaRepository<ActorProfileMediaFile, Long> {

}
