package com.elephant.dreamhi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.CastingDetailDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Casting;
import com.elephant.dreamhi.model.entity.CastingStyleRelation;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.model.entity.Style;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
class CastingRepositoryCustomImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CastingRepository castingRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Test
    @DisplayName("공고ID로 배역 정보와 각 배역별 스타일 정보 조회")
    void findByAnnouncementId() {
        //given
        Producer producer = TestEntityGenerator.createTestProducer();
        entityManager.persist(producer);

        Announcement announcement = TestEntityGenerator.createTestAnnouncement(producer);
        entityManager.persist(announcement);

        Casting casting1 = TestEntityGenerator.createTestCasting(announcement, 1);
        entityManager.persist(casting1);

        List<Style> styles1 = TestEntityGenerator.createTestStyles(1, 4);
        styles1.forEach(entityManager::persist);

        List<CastingStyleRelation> castingStyleRelations1 = TestEntityGenerator.createTestCastingStyleRelations(casting1, styles1);
        castingStyleRelations1.forEach(entityManager::persist);

        Casting casting2 = TestEntityGenerator.createTestCasting(announcement, 2);
        entityManager.persist(casting2);

        List<Style> styles2 = TestEntityGenerator.createTestStyles(3, 5);
        styles2.forEach(entityManager::persist);

        List<CastingStyleRelation> castingStyleRelations2 = TestEntityGenerator.createTestCastingStyleRelations(casting2, styles2);
        castingStyleRelations2.forEach(entityManager::persist);

        entityManager.clear();
        //when
        List<CastingDetailDto> actual = castingRepository.findByAnnouncementId(announcement.getId());

        entityManager.clear();
        //then
        Casting foundCasting1 = castingRepository.findById(casting1.getId())
                                                 .orElseThrow(() -> new NotFoundException("해당 배역을 찾을 수 없습니다."));
        List<Style> foundStyles1 = styleRepository.findAllById(styles1.stream()
                                                                      .map(Style::getId)
                                                                      .collect(Collectors.toList()));

        Casting foundCasting2 = castingRepository.findById(casting2.getId())
                                                 .orElseThrow(() -> new NotFoundException("해당 배역을 찾을 수 없습니다."));
        List<Style> foundStyles2 = styleRepository.findAllById(styles2.stream()
                                                                      .map(Style::getId)
                                                                      .collect(Collectors.toList()));

        List<CastingDetailDto> expected = List.of(
                CastingDetailDto.toDto(foundCasting1, foundStyles1),
                CastingDetailDto.toDto(foundCasting2, foundStyles2)
        );

        assertThat(actual).usingRecursiveComparison()
                          .isEqualTo(expected);
    }

}