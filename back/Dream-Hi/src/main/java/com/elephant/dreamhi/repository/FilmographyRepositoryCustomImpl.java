package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QFilmography.filmography;

import com.elephant.dreamhi.model.dto.FilmographyRequestDto;
import com.elephant.dreamhi.model.dto.FilmographyResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FilmographyRepositoryCustomImpl implements FilmographyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FilmographyResponseDto> findFilmographyByCondition(FilmographyRequestDto condition) {
        return queryFactory.select(Projections.fields(FilmographyResponseDto.class,
                                                      filmography.id,
                                                      filmography.url.as("photoUrl"),
                                                      filmography.title,
                                                      filmography.description
                           ))
                           .from(filmography)
                           .where(producerEq(condition.getProducerId()),
                                  actorEq(condition.getActorId()))
                           .fetch();
    }

    private BooleanExpression actorEq(Long actorId) {
        return (actorId != null) ? filmography.actorProfile.id.eq(actorId) : null;
    }

    private BooleanExpression producerEq(Long producerId) {
        return (producerId != null) ? filmography.producer.id.eq(producerId) : null;
    }

}
