package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Filmography;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmographyRepository extends JpaRepository<Filmography, Long> {

    List<Filmography> findAllByActorProfile_Id(Long id);

    @Query("select f.actorProfile.id from Filmography  f where f.id = :filmographyId")
    Optional<Long> findActorIdById(Long filmographyId);

    @Query("select f.producer.id from Filmography  f where f.id = :filmographyId")
    Optional<Long> findProducerIdById(Long filmographyId);

}
