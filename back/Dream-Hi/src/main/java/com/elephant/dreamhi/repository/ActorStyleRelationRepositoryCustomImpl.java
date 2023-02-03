package com.elephant.dreamhi.repository;



import static com.elephant.dreamhi.model.entity.QActorStyleRelation.actorStyleRelation;

import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ActorStyleRelationRepositoryCustomImpl implements ActorStyleRelationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ActorStyleRelation> findByActorProfileId(Long actorProfileId) {
        return jpaQueryFactory.selectFrom(actorStyleRelation)
                .where(actorStyleRelation.actorProfile.id.eq(actorProfileId))
                .fetch();
    }

}
