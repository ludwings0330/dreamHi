package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QAnnouncement.announcement;
import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QCastingStyleRelation.castingStyleRelation;
import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QProducer.producer;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.statics.Gender;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AnnouncementRepositoryCustomImpl implements AnnouncementRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Announcement> findAnnouncementById(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(announcement)
                            .join(producer).on(announcement.producer.id.eq(producer.id))
                            .where(announcement.id.eq(id))
                            .fetchOne()
        );
    }

    @Override
    public Page<AnnouncementSimpleDto> findAllByCondition(AnnouncementSearchCondition condition, Pageable pageable, Long userId) {
        JPAQuery<AnnouncementSimpleDto> query = queryFactory.select(Projections.constructor(
                                                                    AnnouncementSimpleDto.class,
                                                                    announcement.id,
                                                                    announcement.title,
                                                                    producer.name.as("producerName"),
                                                                    announcement.createdDate,
                                                                    announcement.hit,
                                                                    announcement.castings
                                                            ))
                                                            .from(announcement)
                                                            .join(announcement.producer, producer).fetchJoin()
                                                            .join(casting.announcement, announcement).fetchJoin()
                                                            .where(
                                                                    heightBetween(condition.getMinHeight(), condition.getMaxHeight()),
                                                                    ageBetween(condition.getMinAge(), condition.getMaxAge()),
                                                                    genderEq(condition.getGender()),
                                                                    keywordLike(condition.getKeyword()),
                                                                    styleIn(condition.getStyles()),
                                                                    followEq(condition.getIsFollow(), userId),
                                                                    volunteerEq(condition.getIsVolunteer(), userId)
                                                            )
                                                            .distinct();

        List<AnnouncementSimpleDto> contents = query.orderBy(announcement.id.desc())
                                                    .offset(pageable.getOffset())
                                                    .limit(pageable.getPageSize())
                                                    .fetch();

        if (condition.getIsFollow()) {
            contents.forEach(dto -> dto.setIsFollow(Boolean.TRUE));
        }

        return PageableExecutionUtils.getPage(contents, pageable, () -> query.fetch().size());
    }

    private BooleanExpression heightBetween(Integer minHeight, Integer maxHeight) {
        if (minHeight == null && maxHeight == null) {
            return null;
        }

        if (minHeight != null && maxHeight == null) {
            return casting.minHeight.goe(minHeight.longValue());
        }

        if (minHeight == null) {
            return casting.maxHeight.loe(maxHeight.longValue());
        }

        return casting.minHeight.goe(minHeight.longValue()).and(casting.maxHeight.loe(maxHeight.longValue()));
    }

    private BooleanExpression ageBetween(Integer minAge, Integer maxAge) {
        if (minAge == null && maxAge == null) {
            return null;
        }

        if (minAge != null && maxAge == null) {
            return casting.minHeight.goe(minAge.longValue());
        }

        if (minAge == null) {
            return casting.maxHeight.loe(maxAge.longValue());
        }

        return casting.minHeight.goe(minAge.longValue()).and(casting.maxHeight.loe(maxAge.longValue()));
    }

    private BooleanExpression genderEq(Gender gender) {
        if (gender == null) {
            return null;
        }

        return casting.gender.eq(gender);
    }

    private BooleanExpression keywordLike(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return null;
        }

        String likeKeyword = "%" + keyword + "%";
        return announcement.title.like(likeKeyword).or(producer.name.like(likeKeyword));
    }

    private BooleanExpression styleIn(Long[] styles) {
        if (styles == null || styles.length == 0) {
            return null;
        }

        return casting.id.in(
                JPAExpressions.select(casting.id)
                              .from(casting)
                              .join(castingStyleRelation.casting, casting).fetchJoin()
                              .join(castingStyleRelation.style, style).fetchJoin()
                              .where(style.id.in(styles))
                              .distinct()
        );
    }

    private BooleanExpression followEq(Boolean isFollow, Long followerId) {
        if (isFollow == null || !isFollow) {
            return null;
        }

        return announcement.id.in(
                JPAExpressions.select(announcement.id)
                              .from(announcement)
                              .join(follow.announcement, announcement).fetchJoin()
                              .join(follow.follower, user).fetchJoin()
                              .where(user.id.eq(followerId))
                              .distinct()
        );
    }

    private BooleanExpression volunteerEq(Boolean isVolunteer, Long userId) {
        if (isVolunteer == null || !isVolunteer) {
            return null;
        }

        return casting.id.in(
                JPAExpressions.select(casting.id)
                              .from(casting)
                              .join(volunteer.casting, casting).fetchJoin()
                              .join(volunteer.user, user).fetchJoin()
                              .where(user.id.eq(userId))
                              .distinct()
        );
    }

    @Override
    public Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long followerId) {
        AnnouncementDetailDto findedAnnouncementDetailDto = queryFactory.select(Projections.constructor(
                                                                                AnnouncementDetailDto.class,
                                                                                announcement.id,
                                                                                announcement.title,
                                                                                producer.id.as("producerId"),
                                                                                producer.name.as("producerName"),
                                                                                announcement.payment,
                                                                                announcement.crankPeriod,
                                                                                announcement.endDate,
                                                                                announcement.description,
                                                                                announcement.hit,
                                                                                announcement.picture.url.as("pictureUrl"),
                                                                                new CaseBuilder()
                                                                                        .when(JPAExpressions.select(follow.count()).from(follow).where(
                                                                                                follow.announcement.id.eq(announcementId),
                                                                                                follow.follower.id.eq(followerId)
                                                                                        ).eq(1L))
                                                                                        .then(Boolean.TRUE)
                                                                                        .otherwise(Boolean.FALSE)
                                                                                        .as("isFollowed")
                                                                        )).from(announcement)
                                                                        .join(producer).on(announcement.producer.id.eq(producer.id))
                                                                        .where(
                                                                                announcement.id.eq(announcementId)
                                                                        ).fetchOne();

        return Optional.ofNullable(findedAnnouncementDetailDto);
    }

}
