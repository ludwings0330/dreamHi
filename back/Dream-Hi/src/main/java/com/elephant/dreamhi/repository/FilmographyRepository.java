package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Filmography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmographyRepository extends JpaRepository<Filmography, Long> {

}
