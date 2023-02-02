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
@DisplayName("필모그래피 repository 테스트")
class FilmographyRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private FilmographyRepository filmographyRepository;

    @Autowired
    private FilmographyRepositoryCustom filmographyCustomRepository;

    @Test
    @DisplayName("필모그래피 목록 조회 - 제작사 id")
    public void getFilmographiesByProducerId() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("필모그래피 목록 조회 - 배우 id")
    public void getFilmographiesByActorId() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("필모그래피 id로 배우 id 조회")
    public void getActorIdByFilmographyId() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("필모그래피 id로 제작사 id 조회")
    public void getProducerIdByFilmographyId() throws Exception {
        //given

        //when

        //then

    }

}