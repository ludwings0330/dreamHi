package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QAnnouncement.announcement;
import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QProducer.producer;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
