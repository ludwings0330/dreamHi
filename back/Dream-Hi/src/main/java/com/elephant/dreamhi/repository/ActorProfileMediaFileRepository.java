package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorProfileMediaFileRepository extends JpaRepository<ActorProfileMediaFile, Long> {

    List<ActorProfileMediaFile> findAllByActorProfile_Id(Long id);

}
