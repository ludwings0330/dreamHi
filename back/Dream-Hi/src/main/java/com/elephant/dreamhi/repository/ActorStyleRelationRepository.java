package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorStyleRelationRepository extends JpaRepository<ActorStyleRelation, Long>, ActorStyleRelationRepositoryCustom {

    @Query("DELETE "
            + "FROM ActorStyleRelation asr "
            + "WHERE asr.actorProfile.id = :actorProfileId "
            + "AND asr.style.id in :deleteStyles")
    @Modifying
    Integer deleteAllInStlyeIdQuery(@Param("actorProfileId") Long actorProfileId, @Param("deleteStyles") List<Long> deleteStyles);

}
