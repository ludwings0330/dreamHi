package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long>, ProcessRepositoryCustom {

}
