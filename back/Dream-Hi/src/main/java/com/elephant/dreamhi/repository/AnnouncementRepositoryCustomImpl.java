package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QAnnouncement.announcement;
import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QCastingStyleRelation.castingStyleRelation;
import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QProcess.process;
import static com.elephant.dreamhi.model.entity.QProducer.producer;
import static com.elephant.dreamhi.model.entity.QStyle.style;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QUserProducerRelation.userProducerRelation;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementNameDto;
import com.elephant.dreamhi.model.dto.AnnouncementSearchCondition;
import com.elephant.dreamhi.model.dto.AnnouncementSimpleDto;
import com.elephant.dreamhi.model.dto.CastingSimpleDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.statics.Gender;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AnnouncementRepositoryCustomImpl implements AnnouncementRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Announcement> findByAnnouncementId(Long announcementId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(announcement)
                            .join(announcement.castings, casting).fetchJoin()
                            .where(announcement.id.eq(announcementId))
                            .fetchOne()
        );
    }

    @Override
    public long findTotalCountByCondition(AnnouncementSearchCondition condition, Long userId) {
        return getQueryToFindAnnouncementIdsByCondition(condition, userId).fetch().size();
    }


    @Override
    public List<Long> findAnnouncementIdsByCondition(AnnouncementSearchCondition condition, Pageable pageable, Long userId) {
        return getQueryToFindAnnouncementIdsByCondition(condition, userId).orderBy(announcement.id.desc())
                                                                          .offset(pageable.getOffset())
                                                                          .limit(pageable.getPageSize())
                                                                          .fetch();
    }

    @Override
    public List<AnnouncementSimpleDto> findAllByIds(List<Long> announcementIds, Boolean isFollow) {
        log.info("조회할 공고 ID: {}", announcementIds);

        List<AnnouncementSimpleDto> announcementSimpleDtos = queryFactory.selectFrom(announcement)
                                                                         .join(announcement.producer, producer)
                                                                         .join(announcement.castings, casting)
                                                                         .where(announcement.id.in(announcementIds))
                                                                         .distinct()
                                                                         .transform(
                                                                                 groupBy(announcement.id).list(Projections.constructor(
                                                                                         AnnouncementSimpleDto.class,
                                                                                         announcement.id,
                                                                                         announcement.title,
                                                                                         producer.name,
                                                                                         Expressions.asBoolean(isFollow),
                                                                                         announcement.createdDate,
                                                                                         announcement.hit,
                                                                                         list(Projections.constructor(
                                                                                                 CastingSimpleDto.class,
                                                                                                 casting.headcount,
                                                                                                 casting.name
                                                                                         ))
                                                                                 ))
                                                                         );

        log.info("조회된 공고 목록 : {}", announcementSimpleDtos);
        return announcementSimpleDtos;
    }

    @Override
    public List<AnnouncementNameDto> findWeeklyAnnouncements(LocalDate endDate) {
        return queryFactory.select(Projections.constructor(
                                   AnnouncementNameDto.class,
                                   announcement.id,
                                   announcement.title,
                                   producer.name
                           ))
                           .from(announcement)
                           .join(announcement.producer, producer)
                           .where(announcement.endDate.eq(endDate))
                           .orderBy(announcement.hit.desc())
                           .limit(2)
                           .fetch();
    }

    @Override
    public Optional<AnnouncementDetailDto> findByAnnouncementIdAndFollowerId(Long announcementId, Long userId) {
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
                                                                                ExpressionUtils.as(
                                                                                        new CaseBuilder()
                                                                                                .when(
                                                                                                        JPAExpressions.select(follow.count())
                                                                                                                      .from(follow)
                                                                                                                      .where(
                                                                                                                              follow.announcement.id.eq(announcementId),
                                                                                                                              follow.follower.id.eq(userId)
                                                                                                                      ).eq(1L)
                                                                                                )
                                                                                                .then(Boolean.TRUE)
                                                                                                .otherwise(Boolean.FALSE),
                                                                                        "isFollow"
                                                                                ),
                                                                                Expressions.as(
                                                                                        new CaseBuilder()
                                                                                                .when(
                                                                                                        JPAExpressions.select(userProducerRelation.role)
                                                                                                                      .from(userProducerRelation)
                                                                                                                      .where(
                                                                                                                              userProducerRelation.user.id.eq(userId),
                                                                                                                              userProducerWrite(announcementId)
                                                                                                                      )
                                                                                                                      .eq(ProducerRole.EDITOR)
                                                                                                ).then(Boolean.TRUE)
                                                                                                .otherwise(Boolean.FALSE),
                                                                                        "isEditor"
                                                                                )
                                                                        ))
                                                                        .from(announcement)
                                                                        .join(announcement.producer, producer)
                                                                        .where(announcement.id.eq(announcementId))
                                                                        .fetchOne();

        return Optional.ofNullable(findedAnnouncementDetailDto);
    }

    @Override
    public List<AnnouncementNameDto> findTopAnnouncementsWithRecruiting(final int N) {
        return queryFactory.select(Projections.constructor(
                                   AnnouncementNameDto.class,
                                   announcement.id,
                                   announcement.title,
                                   producer.name
                           ))
                           .from(announcement)
                           .join(announcement.producer, producer)
                           .join(process.announcement, announcement)
                           .where(process.state.eq(ProcessState.RECRUITING))
                           .orderBy(announcement.hit.desc())
                           .limit(N)
                           .fetch();
    }

    private JPAQuery<Long> getQueryToFindAnnouncementIdsByCondition(AnnouncementSearchCondition condition, Long userId) {
        return queryFactory.selectDistinct(announcement.id)
                           .from(announcement)
                           .join(announcement.producer, producer)
                           .join(announcement.castings, casting)
                           .where(
                                   heightBetween(condition.getMinHeight(), condition.getMaxHeight()),
                                   ageBetween(condition.getMinAge(), condition.getMaxAge()),
                                   genderEq(condition.getGender()),
                                   keywordLike(condition.getKeyword()),
                                   styleIn(condition.getStyles()),
                                   followEq(condition.getIsFollow(), userId),
                                   volunteerEq(condition.getIsVolunteer(), userId),
                                   producerEq(condition.getProducerId())
                           );
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

        return announcement.title.contains(keyword)
                                 .or(producer.name.contains(keyword));
    }

    private BooleanExpression styleIn(Long[] styles) {
        if (styles == null || styles.length == 0) {
            return null;
        }

        return casting.id.in(
                JPAExpressions.select(casting.id)
                              .from(casting)
                              .join(casting.castingStyleRelations, castingStyleRelation)
                              .join(castingStyleRelation.style, style)
                              .where(style.id.in(styles))
        );
    }

    private BooleanExpression followEq(Boolean isFollow, Long followerId) {
        if (isFollow == null || !isFollow || followerId == 0L) {
            return null;
        }

        return announcement.id.in(
                JPAExpressions.select(announcement.id)
                              .from(announcement)
                              .join(follow.announcement, announcement)
                              .join(follow.follower, user)
                              .where(user.id.eq(followerId))
        );
    }

    private BooleanExpression volunteerEq(Boolean isVolunteer, Long userId) {
        if (isVolunteer == null || !isVolunteer) {
            return null;
        }

        return casting.id.in(
                JPAExpressions.select(casting.id)
                              .from(volunteer)
                              .join(volunteer.casting, casting)
                              .join(volunteer.user, user)
                              .where(user.id.eq(userId))
        );
    }

    private BooleanExpression producerEq(Long producerId) {
        if (producerId == null || producerId == 0L) {
            return null;
        }

        return producer.id.eq(producerId);
    }

    private BooleanExpression userProducerWrite(Long announcementId) {
        return userProducerRelation.producer.id.eq(
                JPAExpressions.select(producer.id)
                              .from(announcement)
                              .join(announcement.producer, producer)
                              .where(announcement.id.eq(announcementId))
        );
    }

}
