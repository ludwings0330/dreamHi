package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QProducer.producer;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QUserProducerRelation.userProducerRelation;
import static com.querydsl.jpa.JPAExpressions.select;

import com.elephant.dreamhi.model.dto.ProducerListResponseDto;
import com.elephant.dreamhi.model.dto.ProducerMemberDto;
import com.elephant.dreamhi.model.dto.ProducerSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProducerRepositoryCustomImpl implements ProducerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProducerListResponseDto> findProducersByCondition(ProducerSearchCondition condition, Pageable pageable) {
        long totalCount = getQueryByCondition(condition).select(producer.id).fetch().size();
        final List<ProducerListResponseDto> fetch = getQueryByCondition(condition).select(Projections.fields(
                                                                                          ProducerListResponseDto.class,
                                                                                          producer.id,
                                                                                          producer.name,
                                                                                          producer.picture.url.as("pictureUrl")
                                                                                  ))
                                                                                  .offset(pageable.getOffset())
                                                                                  .limit(pageable.getPageSize())
                                                                                  .fetch();

        return PageableExecutionUtils.getPage(fetch, pageable, () -> totalCount);
    }

    @Override
    public List<ProducerMemberDto> findMembersByProducerId(Long producerId) {
        return queryFactory.select(Projections.fields(ProducerMemberDto.class,
                                                      user.name,
                                                      user.picture.url.as("pictureUrl"),
                                                      userProducerRelation.position))
                           .from(userProducerRelation)
                           .join(userProducerRelation.user, user)
                           .where(userProducerRelation.producer.id.eq(producerId)).fetch();
    }

    private BooleanExpression followEq(Boolean isFollow, Long userId) {
        if (isFollow == null || !isFollow) {
            return null;
        }

        return producer.id.in(
                select(follow.producer.id)
                        .from(follow)
                        .where(follow.follower.id.eq(userId)));
    }

    private BooleanExpression nameEq(String name) {
        return (name != null) ? producer.name.contains(name) : null;
    }

    private BooleanExpression involveEq(Long userId, Boolean involve) {
        return (involve != null) ? producer.id.in(select(userProducerRelation.producer.id)
                                                          .from(userProducerRelation)
                                                          .where(userProducerRelation.user.id.eq(userId))) : null;
    }

    private JPAQuery<?> getQueryByCondition(ProducerSearchCondition condition) {
        return queryFactory.from(producer)
                           .where(nameEq(condition.getName()),
                                  followEq(condition.getIsFollow(), condition.getUserId()),
                                  involveEq(condition.getUserId(), condition.getInvolve()));
    }

}
