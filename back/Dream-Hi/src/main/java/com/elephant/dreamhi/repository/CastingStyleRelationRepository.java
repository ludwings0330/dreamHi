package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.CastingStyleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastingStyleRelationRepository extends JpaRepository<CastingStyleRelation, Long> {


}
