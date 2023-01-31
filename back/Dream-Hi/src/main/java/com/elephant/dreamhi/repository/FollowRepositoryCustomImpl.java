package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QUser.user;

import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.model.statics.FollowType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyFollowersDto> findAllByActor_Id(Long actorId) {
        return jpaQueryFactory
                .select(Projections.constructor(
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

    @Override
    public Optional<Follow> checkFollow(FollowType type, Long id, Long followerId) {
        JPAQuery<Follow> query = jpaQueryFactory.selectFrom(follow)
                                                .where(follow.follower.id.eq(followerId));

        query = followFactory(type, id, query);

        return Optional.ofNullable(query.fetchOne());
    }

    @Override
    public Integer deleteFollowWithCondition(FollowType type, Long id, Long followerId) {
        return (int) jpaQueryFactory
                .delete(follow)
                .where(
                        follow.follower.id.eq(followerId),
                        actorEq(type, id),
                        producerEq(type, id),
                        announcementEq(type, id)
                )
                .execute();
    }

    private BooleanExpression actorEq(FollowType type, Long id) {
        return type == FollowType.ACTOR ? follow.actor.id.eq(id) : null;
    }

    private BooleanExpression producerEq(FollowType type, Long id) {
        return type == FollowType.PRODUCER ? follow.producer.id.eq(id) : null;
    }

    private BooleanExpression announcementEq(FollowType type, Long id) {
        return type == FollowType.ANNOUNCEMENT ? follow.announcement.id.eq(id) : null;
    }

    /**
     * type에 따라 쿼리문 생성 메소드
     *
     * @param type
     * @param id
     * @param query
     * @return JPAQuery<Follow>
     */
    private static JPAQuery<Follow> followFactory(FollowType type, Long id, JPAQuery<Follow> query) {
        switch (type) {
            case ACTOR:
                query = query.where(
                        follow.actor.id.eq(id)
                );
                break;
            case ANNOUNCEMENT:
                query = query.where(
                        follow.announcement.id.eq(id)
                );
                break;
            case PRODUCER:
                query = query.where(
                        follow.producer.id.eq(id)
                );
                break;
            default:
                break;
        }
        return query;
    }

}
