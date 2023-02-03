package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StyleRepository extends JpaRepository<Style, Long> {

}