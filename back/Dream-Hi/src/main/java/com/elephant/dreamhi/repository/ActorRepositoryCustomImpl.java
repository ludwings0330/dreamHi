package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QActorStyleRelation.actorStyleRelation;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.elephant.dreamhi.model.dto.ActorListResponseDto;
import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.StyleDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.QFollow;
import com.elephant.dreamhi.model.statics.Gender;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ActorRepositoryCustomImpl implements ActorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ActorProfile> findActorProfileDetailByUser_Id(Long userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(actorProfile)
                                                  .join(actorProfile.user, user).fetchJoin()
                                                  .leftJoin(actorProfile.actorStyleRelations, actorStyleRelation).fetchJoin()
                                                  .leftJoin(actorStyleRelation.style, style).fetchJoin()
                                                  .distinct()
                                                  .where(actorProfile.user.id.eq(userId))
                                                  .fetchOne());
    }

    @Override
    public Page<ActorListResponseDto> findActorsWithFiltering(ActorSearchCondition condition, Pageable pageable) {
        JPAQuery<Long> query = findActorsIndexWithFiltering(condition, pageable);
        HashSet<Long> actorIds = new HashSet<>(query.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch());

        Long totalSize = Long.valueOf(query.fetch().size());
        if (actorIds.isEmpty()) {
            return PageableExecutionUtils.getPage(new ArrayList<>(), pageable, () -> totalSize);
        }

        List<ActorListResponseDto> actorListResponseDto = jpaQueryFactory
                .selectFrom(actorProfile)
                .join(actorProfile.user, user)
                .leftJoin(actorStyleRelation)
                .on(actorStyleRelation.actorProfile.id.eq(actorProfile.id))
                .leftJoin(actorStyleRelation.style, style)
                .where(actorProfile.id.in(actorIds))
                .transform(
                        groupBy(actorProfile.id).list(
                                Projections.constructor(
                                        ActorListResponseDto.class,
                                        user.id.as("userId"),
                                        user.name,
                                        user.picture.url.as("pictureUrl"),
                                        actorProfile.id.as("actorProfileId"),
                                        actorProfile.title,
                                        actorProfile.gender,
                                        actorProfile.age,
                                        actorProfile.height,
                                        list(
                                                Projections.constructor(StyleDto.class,
                                                                        style.id,
                                                                        style.description
                                                )
                                        ),
                                        Expressions.asBoolean(false).as("isFollow")
                                )
                        )
                );

        return PageableExecutionUtils.getPage(actorListResponseDto, pageable, () -> totalSize);
    }

    private JPAQuery<Long> findActorsIndexWithFiltering(ActorSearchCondition condition, Pageable pageable) {
        return jpaQueryFactory.selectDistinct(actorProfile.id)
                              .from(actorProfile)
                              .join(actorProfile.user, user).on(actorProfile.visible.isTrue())
                              .leftJoin(actorStyleRelation).on(actorStyleRelation.actorProfile.id.eq(actorProfile.id))
                              .leftJoin(actorStyleRelation.style, style)
                              .where(nameEq(condition.getName()),
                                     heightEq(condition.getHeight()),
                                     ageEq(condition.getAge()),
                                     genderEq(condition.getGender()),
                                     followEq(condition.getIsFollow(), condition.getId()),
                                     stylesEq(condition.getStyles())
                              )
                              .distinct()
                              .orderBy(actorProfile.id.asc());
    }

    @Override
    public Optional<ActorProfile> checkValidateModify(Long id, Long userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(actorProfile)
                                                  .where(actorProfile.id.eq(id),
                                                         actorProfile.user.id.eq(userId))
                                                  .fetchOne()
        );
    }

    private BooleanExpression followEq(Boolean isFollow, Long id) {
        if (id == 0L || isFollow == null || !isFollow) {
            return null;
        }
        return user.id.in(
                JPAExpressions.select(QFollow.follow.actor.id)
                              .from(QFollow.follow)
                              .where(QFollow.follow.follower.id.eq(id))
        );
    }

    private BooleanExpression stylesEq(List<Long> styles) {
        if (styles == null || styles.size() == 0) {
            return null;
        }

        return actorProfile.id.in(
                JPAExpressions.select(actorProfile.id)
                              .from(actorProfile)
                              .join(actorProfile.actorStyleRelations, actorStyleRelation)
                              .join(actorStyleRelation.style, style)
                              .where(style.id.in(
                                      styles
                              )));
    }

    private BooleanExpression genderEq(Gender gender) {
        return gender != null ? actorProfile.gender.eq(gender) : null;
    }

    private BooleanExpression ageEq(Integer age) {
        return age != null ? actorProfile.age.between(age - 3, age + 3) : null;
    }

    private BooleanExpression heightEq(Integer height) {
        return height != null ? actorProfile.height.between(height - 3, height + 3) : null;
    }

    private BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? actorProfile.user.name.contains(name) : null;
    }

}
