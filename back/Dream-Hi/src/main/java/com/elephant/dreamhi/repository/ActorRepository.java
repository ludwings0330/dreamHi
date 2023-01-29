package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorProfile, Long>, ActorRepositoryCustom {

    ActorProfile findByUser_id(Long id);

    Optional<ActorProfile> findActorProfileById(Long id);

}
