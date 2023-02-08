package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Casting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CastingRepository extends JpaRepository<Casting, Long>, CastingRepositoryCustom {

    @Modifying
    @Query("DELETE Casting c where c.id = :castingId")
    void deleteById(Long castingId);

}
