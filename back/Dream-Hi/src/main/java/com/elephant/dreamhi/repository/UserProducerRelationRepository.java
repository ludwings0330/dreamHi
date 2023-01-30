package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.UserProducerRelation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProducerRelationRepository extends JpaRepository<UserProducerRelation, Long> {

    UserProducerRelation save(UserProducerRelation upr);

    List<UserProducerRelation> findAllByUser_Id(Long userId);

    Optional<UserProducerRelation> findByProducer_IdAndUser_Id(Long userId, Long producerId);

    void deleteByProducer_IdAndUser_Id(Long producerId, Long userId);

}
