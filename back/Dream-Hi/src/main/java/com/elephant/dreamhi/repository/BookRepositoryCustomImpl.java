package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QActorProfile.actorProfile;
import static com.elephant.dreamhi.model.entity.QBook.book;
import static com.elephant.dreamhi.model.entity.QUser.user;
import static com.elephant.dreamhi.model.entity.QVolunteer.volunteer;

import com.elephant.dreamhi.model.dto.BookPeriodDto;
import com.elephant.dreamhi.model.dto.BookProducerDto;
import com.elephant.dreamhi.model.dto.BookResponseDto;
import com.elephant.dreamhi.model.dto.BookedVolunteerDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private static final StringTemplate START_DATE = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            book.startTime,
            ConstantImpl.create("%Y-%m-%d")
    );

    private static final StringTemplate START_TIME = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            book.startTime,
            ConstantImpl.create("%H:%i:%s")
    );

    private static final StringTemplate END_DATE = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            book.endTime,
            ConstantImpl.create("%Y-%m-%d")
    );

    private static final StringTemplate END_TIME = Expressions.stringTemplate(
            "DATE_FORMAT({0}, {1})",
            book.endTime,
            ConstantImpl.create("%H:%i:%s")
    );

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BookPeriodDto> findBookPeriodByProcessId(Long processId) {
        return Optional.ofNullable(
                queryFactory.select(Projections.constructor(
                                    BookPeriodDto.class,
                                    START_DATE.min(),
                                    END_DATE.max()
                            ))
                            .from(book)
                            .where(book.process.id.eq(processId))
                            .fetchOne()
        );
    }

    @Override
    public List<BookResponseDto> findAllForVolunteerByProcessIdAndDate(Long processId, LocalDate date) {
        return queryFactory.select(Projections.constructor(BookResponseDto.class,
                                                           book.id,
                                                           START_TIME,
                                                           END_TIME,
                                                           Expressions.asBoolean(book.reserved)
                           ))
                           .from(book)
                           .where(book.process.id.eq(processId),
                                  START_DATE.eq(date.toString()))
                           .fetch();
    }

    @Override
    public List<BookProducerDto> findAllForProducerByProducerIdAndDate(Long processId, LocalDate date) {
        return queryFactory.select(Projections.constructor(BookProducerDto.class,
                                                           book.id,
                                                           START_TIME,
                                                           END_TIME,
                                                           Expressions.asBoolean(book.reserved),
                                                           volunteer.user.id
                           ))
                           .from(book)
                           .join(book.volunteer, volunteer)
                           .where(book.process.id.eq(processId),
                                  START_DATE.eq(date.toString()))
                           .fetch();
    }

    @Override
    public List<BookedVolunteerDto> findByProcessIdAndBookDate(Long processId, LocalDate today) {
        return queryFactory.select(Projections.constructor(BookedVolunteerDto.class,
                                                           user.id,
                                                           user.name,
                                                           actorProfile.gender,
                                                           actorProfile.age,
                                                           actorProfile.height,
                                                           volunteer.state,
                                                           START_TIME,
                                                           END_TIME))
                           .from(book)
                           .join(book.volunteer, volunteer).fetchJoin()
                           .join(volunteer.user, user).fetchJoin()
                           .join(actorProfile.user, user).fetchJoin()
                           .where(book.process.id.eq(processId),
                                  START_DATE.eq(today.toString()))
                           .fetch();
    }

}
