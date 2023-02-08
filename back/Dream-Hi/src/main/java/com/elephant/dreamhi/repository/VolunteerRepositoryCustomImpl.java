package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QCasting.casting;
import static com.elephant.dreamhi.model.entity.QProcess.process;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;

import com.elephant.dreamhi.model.dto.QVolunteerSearchResponseDto_VolunteerSimpleInfo;
import com.elephant.dreamhi.model.dto.VolunteerSearchCondition;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto;
import com.elephant.dreamhi.model.dto.VolunteerSearchResponseDto.VolunteerSimpleInfo;
import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.QFollow;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.VolunteerState;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
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
        return null;
    }

    @Override
    public Page<VolunteerSimpleInfo> findVolunteersByCondition(VolunteerSearchCondition condition) {
        final JPAQuery<VolunteerSimpleInfo> query = queryFactory.select(
                                                                        new QVolunteerSearchResponseDto_VolunteerSimpleInfo(
                                                                                user.id, user.picture.url, user.name, volunteer.state,
                                                                                actorProfile.height, actorProfile.age,
                                                                                actorProfile.id.in(
                                                                                        JPAExpressions.select(QFollow.follow.actor.id)
                                                                                                      .from(QFollow.follow)
                                                                                                      .where(QFollow.follow.follower.id.eq(condition.getUserId()))
                                                                                )
                                                                        )
                                                                )
                                                                .from(volunteer)
                                                                .join(volunteer.user, user)
                                                                .join(actorProfile).on(actorProfile.user.id.eq(user.id))
                                                                .where(volunteer.process.id.eq(condition.getProcessId()),
                                                                       volunteer.casting.id.eq(condition.getCastingId()),
                                                                       stateEq(condition.getState()));
        final List<VolunteerSimpleInfo> fetch = query.offset(condition.getPageable().getOffset())
                                                     .limit(condition.getPageable().getPageSize())
                                                     .fetch();

        return PageableExecutionUtils.getPage(fetch, condition.getPageable(), () -> query.fetch().size());
    }

    @Override
    public void updatePassVolunteers(Long announcementId, Process process) {
        queryFactory.update(volunteer)
                    .set(volunteer.process, process)
                    .set(volunteer.state, VolunteerState.NONE)
                    .where(volunteer.announcement.id.eq(announcementId),
                           volunteer.state.eq(VolunteerState.PASS))
                    .execute();
    }

//    @Override
//    public Long countPassVolunteersByCastingId(Long castingId) {
//        return queryFactory.select(volunteer.count())
//                           .from(volunteer)
//                           .where(volunteer.casting.id.eq(castingId),
//                                  volunteer.state.eq(VolunteerState.PASS))
//                           .fetchOne();
//    }

    @Override
    public void updateAll(Long announcementId, Process process) {
        queryFactory.update(volunteer)
                    .set(volunteer.process, process)
                    .where(volunteer.announcement.id.eq(announcementId))
                    .execute();
    }

    private BooleanExpression stateEq(VolunteerState state) {
        return (state == null) ? null : volunteer.state.eq(state);
    }

}
