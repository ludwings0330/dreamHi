package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.UserProducerRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProducerRelationRepository extends JpaRepository<UserProducerRelation, Long> {

    UserProducerRelation save(UserProducerRelation upr);

    List<UserProducerRelation> findAllByUser_Id(Long userId);

}
