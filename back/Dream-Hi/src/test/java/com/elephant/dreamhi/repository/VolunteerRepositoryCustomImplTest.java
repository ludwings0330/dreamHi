package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
class VolunteerRepositoryCustomImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Test
    void findByUserIdAndAnnouncementId() {
        volunteerRepository.findByUserIdAndAnnouncementId(1L, 1L);
    }

}