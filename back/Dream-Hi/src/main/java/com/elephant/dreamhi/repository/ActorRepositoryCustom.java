package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.entity.ActorProfile;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorRepositoryCustom {

    Optional<ActorProfile> findActorProfileDetailByUser_Id(Long userId);

    Page<ActorProfile> findActorSimpleProfiles(ActorSearchCondition condition, Pageable pageable);

    Optional<ActorProfile> checkValidateModify(Long id, Long userId);

}
