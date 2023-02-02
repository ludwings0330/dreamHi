package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QProcess.process;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;

import com.elephant.dreamhi.model.entity.Volunteer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VolunteerRepositoryCustomImpl implements VolunteerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Volunteer> findByUserIdAndAnnouncementId(Long userId, Long announcementId) {
        return queryFactory.selectFrom(volunteer)
                           .join(volunteer.casting, casting).fetchJoin()
                           .join(volunteer.process, process).fetchJoin()
                           .where(volunteer.user.id.eq(userId), casting.announcement.id.eq(announcementId))
                           .distinct()
                           .fetch();
    }

}
