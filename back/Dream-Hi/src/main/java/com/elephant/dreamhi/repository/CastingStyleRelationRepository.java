package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.CastingStyleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CastingStyleRelationRepository extends JpaRepository<CastingStyleRelation, Long> {

    @Modifying
    @Query("DELETE FROM CastingStyleRelation csr WHERE csr.casting.id = :castingId")
    void deleteAllByCastingId(Long castingId);

}
