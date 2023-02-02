package com.elephant.dreamhi.querydsltest;

import com.elephant.dreamhi.model.entity.QUser;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QueryDslTest.class)
public class QueryDslTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("queryDSL 설정 테스트")
    public void queryDsl() throws Exception {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        User user = User.builder().email("ludwings@naver.com").name("배창민").phone("01012341234").password("1234").build();
        userRepository.save(user);

        final List<User> users = jpaQueryFactory.selectFrom(QUser.user).where(QUser.user.name.eq("배창민")).fetch();

        Assertions.assertThat(users.get(0).getName()).isEqualTo("배창민");
    }

}
