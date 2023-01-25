package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QActorStyleRelation.actorStyleRelation;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.elephant.dreamhi.model.entity.QUser.user;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.model.entity.QFollow;
import com.elephant.dreamhi.model.statics.Gender;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public Optional<ActorProfile> findActorProfileById(Long id) {
        final List<ActorProfile> fetch = jpaQueryFactory.selectFrom(actorProfile)
                                                        .join(actorProfile.user, user).fetchJoin()
                                                        .join(actorProfile.actorStyleRelations, actorStyleRelation).fetchJoin()
                                                        .join(actorStyleRelation.style, style).fetchJoin()
                                                        .distinct()
                                                        .where(actorProfile.id.eq(id))
                                                        .fetch();

        return Optional.of(fetch.get(0));
    }

    @Override
    public Page<ActorProfile> findActorSimpleProfiles(ActorSearchCondition condition, Pageable pageable) {
        // 이름, 나이, 성별, 키, 스타일, 팔로우 여부
        JPAQuery<ActorProfile> query = jpaQueryFactory.select(actorProfile)
                                                      .from(actorProfile)
                                                      .innerJoin(actorProfile.user, user).fetchJoin()
                                                      .leftJoin(actorProfile.actorStyleRelations, actorStyleRelation).fetchJoin()
                                                      .leftJoin(actorStyleRelation.style, style).fetchJoin()
                                                      .where(nameEq(condition.getName()),
                                                             ageEq(condition.getAge()),
                                                             heightEq(condition.getHeight()),
                                                             genderEq(condition.getGender()),
                                                             followEq(condition.getIsFollow(), condition.getId()),
                                                             stylesEq(condition.getStyles()));
        final List<ActorProfile> contents = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        final List<Follow> fetch = jpaQueryFactory.selectFrom(QFollow.follow)
                                                  .join(QFollow.follow.follower, user)
                                                  .where(QFollow.follow.follower.id.eq(condition.getId()),
                                                         QFollow.follow.follower.)
                                                  .fetch();
        return PageableExecutionUtils.getPage(contents, pageable, () -> query.fetch().size());
    }

    private BooleanExpression followEq(Boolean isFollow, Long id) {
        if (isFollow == null || !isFollow) {
            return null;
        }
        return null;
//        return user.followers.contains(
//                JPAExpressions.selectFrom(QFollow.follow)
//                              .join(QFollow.follow.follower)
//                              .where(QFollow.follow.follower.id.eq(id))
//        );
    }

    private BooleanBuilder stylesEq(String[] styles) {
        if (styles == null) {
            return null;
        }

        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        for (String style :
                styles) {
            booleanBuilder.or(actorStyleRelation.style.description.contains(style));
        }

        return booleanBuilder;
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
