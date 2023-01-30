package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QAnnouncement.announcement;
import static com.elephant.dreamhi.model.entity.QFollow.follow;
import static com.elephant.dreamhi.model.entity.QProducer.producer;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnnouncementRepositoryCustomImpl implements AnnouncementRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<AnnouncementDetailDto> findById(Long followerId, Long announcementId) {
        AnnouncementDetailDto findedAnnouncementDetailDto = jpaQueryFactory.select(Projections.fields(
                                                                                   AnnouncementDetailDto.class,
                                                                                   announcement.id,
                                                                                   announcement.title,
                                                                                   announcement.payment,
                                                                                   announcement.crankPeriod,
                                                                                   announcement.endDate,
                                                                                   announcement.description,
                                                                                   announcement.hit,
                                                                                   announcement.picture.url.as("pictureUrl"),
                                                                                   follow.id,
                                                                                   producer.id
                                                                           )).from(announcement)
                                                                           .join(producer).on(announcement.producer.id.eq(producer.id))
                                                                           .leftJoin(follow).on(announcement.id.eq(follow.announcement.id))
                                                                           .where(
                                                                                   announcement.id.eq(announcementId),
                                                                                   follow.follower.id.eq(followerId)
                                                                           ).fetchOne();
        return Optional.ofNullable(findedAnnouncementDetailDto);
    }

}
