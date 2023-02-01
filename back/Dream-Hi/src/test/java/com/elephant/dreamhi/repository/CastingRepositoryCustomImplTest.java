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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
class CastingRepositoryCustomImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CastingRepository castingRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Test
    @DisplayName("공고ID로 배역 정보와 각 배역별 스타일 정보 조회")
    void findByAnnouncementId() {
        //given
        Producer producer = createTestProducer();
        entityManager.persist(producer);

        Announcement announcement = createTestAnnouncement(producer);
        entityManager.persist(announcement);

        Casting casting1 = createTestCasting1(announcement);
        entityManager.persist(casting1);

        List<Style> styles1 = createTestStyles1();
        styles1.forEach(entityManager::persist);

        List<CastingStyleRelation> castingStyleRelations1 = createTestCastingStyleRelations(casting1, styles1);
        castingStyleRelations1.forEach(entityManager::persist);

        Casting casting2 = createTestCasting2(announcement);
        entityManager.persist(casting2);

        List<Style> styles2 = createTestStyles2();
        styles2.forEach(entityManager::persist);

        List<CastingStyleRelation> castingStyleRelations2 = createTestCastingStyleRelations(casting2, styles2);
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

    private Producer createTestProducer() {
        return Producer.builder()
                       .name("더미 제작사")
                       .build();
    }

    private Announcement createTestAnnouncement(Producer producer) {
        return Announcement.builder()
                           .endDate(LocalDateTime.of(2023, 2, 10, 14, 34, 23))
                           .title("더미 공고")
                           .producer(producer)
                           .build();
    }

    private Casting createTestCasting1(Announcement announcement) {
        return Casting.builder()
                      .name("더미 배역1")
                      .announcement(announcement)
                      .description("더미 배역1 역할을 수행합니다.")
                      .headcount(3)
                      .build();
    }

    private Casting createTestCasting2(Announcement announcement) {
        return Casting.builder()
                      .name("더미 배역2")
                      .announcement(announcement)
                      .description("더미 배역2 역할을 수행합니다.")
                      .headcount(5)
                      .build();
    }

    private List<Style> createTestStyles1() {
        Style style1 = Style.builder().description("더미 스타일1").build();
        Style style2 = Style.builder().description("더미 스타일2").build();
        Style style3 = Style.builder().description("더미 스타일3").build();
        return List.of(style1, style2, style3);
    }

    private List<Style> createTestStyles2() {
        Style style1 = Style.builder().description("더미 스타일1").build();
        Style style2 = Style.builder().description("더미 스타일4").build();
        return List.of(style1, style2);
    }

    private List<CastingStyleRelation> createTestCastingStyleRelations(Casting castings, List<Style> styles) {
        List<CastingStyleRelation> castingStyleRelations = new ArrayList<>();

        for (int size = styles.size(), i = 0; i < size; i++) {
            CastingStyleRelation castingStyleRelation = new CastingStyleRelation();
            castingStyleRelation.setStyle(styles.get(i));
            castingStyleRelation.setCasting(castings);
            castingStyleRelations.add(castingStyleRelation);
        }

        return castingStyleRelations;
    }

}