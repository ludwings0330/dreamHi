package com.elephant.dreamhi.repository;

import static com.elephant.dreamhi.model.entity.QUserProducerRelation.userProducerRelation;

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

}
