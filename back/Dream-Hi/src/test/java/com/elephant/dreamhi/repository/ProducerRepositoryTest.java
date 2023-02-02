package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QueryDslTestConfig.class)
@DisplayName("제작사 Repository 테스트")
class ProducerRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    @DisplayName("제작사 목록 이름으로 조회")
    public void getProducersByName() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("제작사 목록 팔로우 여부로 조회 - 팔로우 O")
    public void getProducersByFollow() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("제작사 목록 팔로우 여부로 조회 - 팔로우 X")
    public void getProducersByNotFollow() throws Exception {
        //given

        //when

        //then

    }

}