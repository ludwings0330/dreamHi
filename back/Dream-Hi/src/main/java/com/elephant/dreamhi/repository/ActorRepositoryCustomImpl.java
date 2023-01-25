package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QActorStyleRelation.actorStyleRelation;
import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.elephant.dreamhi.model.entity.QUser.user;

import com.elephant.dreamhi.model.dto.ActorSearchCondition;
import com.elephant.dreamhi.model.dto.ActorSimpleProfileDto;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.QActorProfile;
import com.elephant.dreamhi.model.statics.Gender;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ActorRepositoryCustomImpl implements ActorRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ActorProfile> findActorProfileById(Long id) {
        final ActorProfile actorProfile;
        actorProfile = jpaQueryFactory.selectFrom(QActorProfile.actorProfile)
                                      .join(QActorProfile.actorProfile.user, user).fetchJoin()
                                      .where(QActorProfile.actorProfile.id.eq(id))
                                      .fetchOne();
        return Optional.of(actorProfile);
    }

    @Override
    public Page<ActorSimpleProfileDto> findActorSimpleProfiles(ActorSearchCondition condition, Pageable pageable) {
        final List<ActorProfile> fetch = jpaQueryFactory.selectFrom(actorProfile)
                                                        .join(actorProfile.actorStyleRelations, actorStyleRelation).fetchJoin()
                                                        .join(actorStyleRelation.style, style).fetchJoin()
                                                        .where(actorProfile.visible.eq(true),
                                                               nameEq(condition.getName()),
                                                               heightEq(condition.getHeight()),
                                                               ageEq(condition.getAge()),
                                                               genderEq(condition.getGender()),
                                                               stylesEq(condition.getStyles()))
                                                        .distinct()
                                                        .offset(pageable.getOffset()).limit(pageable.getPageSize() + 1)
                                                        .fetch();

        return new PageImpl<>(null, pageable, 0);
    }

    private BooleanExpression followEq(Boolean isFollow) {
        return isFollow ? follow.follower.eq(actorProfile.user) : null;
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
        return StringUtils.hasText(name) ? actorProfile.user.name.eq(name) : null;
    }

}
