package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QProcess.process;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;

import com.elephant.dreamhi.model.dto.QVolunteerSearchResponseDto;
import com.elephant.dreamhi.model.dto.QVolunteerSearchResponseDto_VolunteerSimpleInfo;
import com.elephant.dreamhi.model.dto.VolunteerSearchCondition;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.VolunteerState;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<VolunteerState, Long> getVolunteerStateSummary(VolunteerSearchCondition condition) {
        return queryFactory.from(volunteer)
                           .where(volunteer.casting.id.eq(condition.getCastingId()),
                                  volunteer.process.id.eq(condition.getProcessId()))
                           .groupBy(volunteer.state)
                           .transform(GroupBy.groupBy(volunteer.state).as(volunteer.state.count()));
    }

    @Override
    public VolunteerSearchResponseDto getVolunteersByCondition(VolunteerSearchCondition condition) {
        queryFactory.from(volunteer)
                    .join(volunteer.user, user)
                    .join(actorProfile).on(actorProfile.user.id.eq(user.id))
                    .where(volunteer.casting.id.eq(condition.getCastingId()),
                           volunteer.process.id.eq(condition.getProcessId()))
                    .transform(GroupBy.groupBy(volunteer.casting.id).as(
                            new QVolunteerSearchResponseDto(
                                    volunteer.casting.id,
                                    volunteer.casting.name,
                                    new QVolunteerSearchResponseDto_VolunteerSimpleInfo(
                                            user.id, user.picture.url, user.name, volunteer.state,
                                            actorProfile.height, actorProfile.age, actorProfile.id.eq(1L)))));
        return null;
    }

}
