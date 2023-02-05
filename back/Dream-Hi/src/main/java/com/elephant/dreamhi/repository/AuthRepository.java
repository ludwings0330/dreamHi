package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QFilmography.filmography;
import static com.elephant.dreamhi.model.entity.QUserProducerRelation.userProducerRelation;

import com.elephant.dreamhi.model.entity.QAnnouncement;
import com.elephant.dreamhi.model.statics.ProducerRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ProducerRole> findRoleByUser_IdAndProducer_Id(Long userId, Long producerId) {
        final ProducerRole role = queryFactory.select(userProducerRelation.role)
                                              .from(userProducerRelation)
                                              .where(userProducerRelation.user.id.eq(userId),
                                                     userProducerRelation.producer.id.eq(producerId))
                                              .fetchOne();
        return Optional.ofNullable(role);
    }

    public Optional<Long> findActorProfileByUserId(Long userId) {
        Long fetchOne = queryFactory.select(actorProfile.id)
                                    .from(actorProfile)
                                    .where(actorProfile.user.id.eq(userId))
                                    .fetchOne();

        return Optional.ofNullable(fetchOne);
    }

    public Optional<Long> findProducerIdByFilmographyId(Long filmographyId) {
        Long find = queryFactory.select(filmography.producer.id)
                                .from(filmography)
                                .where(filmography.id.eq(filmographyId))
                                .fetchOne();

        return Optional.ofNullable(find);
    }

    public Optional<Long> findActorIdByFilmographyId(Long filmographyId) {
        Long find = queryFactory.select(filmography.actorProfile.id)
                                .from(filmography)
                                .where(filmography.id.eq(filmographyId)).fetchOne();

        return Optional.ofNullable(find);
    }

    public Optional<Long> findProducerIdByAnnouncementId(Long announcementId) {
        Long find = queryFactory.select(QAnnouncement.announcement.producer.id)
                                .from(QAnnouncement.announcement)
                                .where(QAnnouncement.announcement.id.eq(announcementId)).fetchOne();

        return Optional.ofNullable(find);
    }

}
