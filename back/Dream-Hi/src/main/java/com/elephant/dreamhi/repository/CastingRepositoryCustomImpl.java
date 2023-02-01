package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QCastingStyleRelation.castingStyleRelation;
import static com.elephant.dreamhi.model.entity.QStyle.style;

import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.entity.Casting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CastingRepositoryCustomImpl implements CastingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CastingDetailDto> findByAnnouncementId(Long announcementId) {
        List<Casting> castings = queryFactory.selectFrom(casting)
                                             .join(casting.castingStyleRelations, castingStyleRelation).fetchJoin()
                                             .join(castingStyleRelation.style, style).fetchJoin()
                                             .where(casting.announcement.id.eq(announcementId))
                                             .distinct()
                                             .fetch();

        return castings.stream()
                       .map(CastingDetailDto::new)
                       .collect(Collectors.toList());
    }

}
