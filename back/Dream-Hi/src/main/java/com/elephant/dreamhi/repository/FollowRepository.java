package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Follow;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByActor_IdAndFollower_Id(Long actorId, Long follwerId);

}
