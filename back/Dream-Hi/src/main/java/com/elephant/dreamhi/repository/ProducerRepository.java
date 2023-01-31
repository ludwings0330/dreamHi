package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Producer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long>, ProducerRepositoryCustom {

    Optional<Producer> findByName(String name);

}
