package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorRepositoryCustom {

    Page<ActorSimpleProfileDto> findActorSimpleProfiles(ActorSearchCondition condition, Pageable pageable);

}
