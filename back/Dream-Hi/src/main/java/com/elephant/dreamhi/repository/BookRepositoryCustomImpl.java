package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QBook.book;

import com.elephant.dreamhi.model.dto.BookPeriod;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BookPeriod> findBookPeriodByProcessId(Long processId) {
        StringTemplate startTimeTemplate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                book.startTime,
                ConstantImpl.create("%Y-%m-%d")
        );

        StringTemplate endTimeTemplate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                book.endTime,
                ConstantImpl.create("%Y-%m-%d")
        );

        return Optional.ofNullable(
                queryFactory.select(Projections.constructor(
                                    BookPeriod.class,
                                    startTimeTemplate.min(),
                                    endTimeTemplate.max()
                            ))
                            .from(book)
                            .where(book.process.id.eq(processId))
                            .fetchOne()
        );
    }

}
