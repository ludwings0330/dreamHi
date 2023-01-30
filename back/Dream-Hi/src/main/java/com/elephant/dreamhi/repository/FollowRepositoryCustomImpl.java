package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QUser.user;

import com.elephant.dreamhi.model.dto.FollowRequestDto;
import com.elephant.dreamhi.model.dto.MyFollowersDto;
import com.elephant.dreamhi.model.entity.Follow;
import com.querydsl.core.types.Projections;
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
    public Optional<Follow> checkFollow(FollowRequestDto followRequestDto, Long followerId) {
        JPAQuery<Follow> query = jpaQueryFactory.selectFrom(follow)
                                                .where(follow.follower.id.eq(followerId));

        query = followFactory(followRequestDto, query);

        return Optional.ofNullable(query.fetchOne());
    }

    /**
     * type에 따라 쿼리문 생성 메소드
     *
     * @param followRequestDto
     * @param query
     * @return JPAQuery<Follow>
     */
    private static JPAQuery<Follow> followFactory(FollowRequestDto followRequestDto, JPAQuery<Follow> query) {
        switch (followRequestDto.getType()) {
            case ACTOR:
                query = query.where(
                        follow.actor.id.eq(followRequestDto.getActorId())
                );
                break;
            case ANNOUNCEMENT:
                query = query.where(
                        follow.announcement.id.eq(followRequestDto.getAnnouncementId())
                );
                break;
            case PRODUCER:
                query = query.where(
                        follow.producer.id.eq(followRequestDto.getProducerId())
                );
                break;
            default:
                break;
        }
        return query;
    }

}
