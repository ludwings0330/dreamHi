package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorStyleRelationRepository extends JpaRepository<ActorStyleRelation, Long> {

}
