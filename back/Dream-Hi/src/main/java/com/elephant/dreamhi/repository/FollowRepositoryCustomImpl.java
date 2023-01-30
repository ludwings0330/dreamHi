package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QUser.user;

import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FolloweReositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyFollowersDto> findAllByActor_Id(Long actorId) {
        return jpaQueryFactory.select(Projections.constructor(
                MyFollowersDto.class,
                follow.id,
                follow.date,
                user.id,
                user.email,
                user.name,
                user.picture.url
        ))
                .from(follow).innerJoin(user).on(follow.follower.id.eq(user.id))
                .orderBy(follow.date.desc())
                .fetch();
    }

}
