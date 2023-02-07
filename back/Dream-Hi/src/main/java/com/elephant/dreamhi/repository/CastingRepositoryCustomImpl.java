package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QCastingStyleRelation.castingStyleRelation;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.dto.StyleDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CastingRepositoryCustomImpl implements CastingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CastingDetailDto> findByAnnouncementId(Long announcementId) {
        return queryFactory.selectFrom(casting)
                           .join(casting.castingStyleRelations, castingStyleRelation)
                           .join(castingStyleRelation.style, style)
                           .where(casting.announcement.id.eq(announcementId))
                           .transform(
                                   groupBy().list(Projections.constructor(
                                           CastingDetailDto.class,
                                           casting.id,
                                           casting.name,
                                           casting.description,
                                           casting.headcount,
                                           casting.minHeight,
                                           casting.maxHeight,
                                           casting.minAge,
                                           casting.maxAge,
                                           casting.gender,
                                           list(Projections.constructor(
                                                   StyleDto.class,
                                                   style.id,
                                                   style.description
                                           )).as("styles")
                                   ))
                           );
    }

    @Override
    public List<Long> findIdByAnnouncementId(Long announcementId) {
        return queryFactory.select(casting.id)
                           .from(casting)
                           .where(casting.announcement.id.eq(announcementId))
                           .fetch();
    }

}
