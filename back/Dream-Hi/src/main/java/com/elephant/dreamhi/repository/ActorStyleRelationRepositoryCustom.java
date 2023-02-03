package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import java.util.List;

public interface ActorStyleRelationRepositoryCustom {

    List<ActorStyleRelation> findByActorProfileId(Long actorProfileId);

}
