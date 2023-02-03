package com.elephant.dreamhi.styleTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.repository.ActorRepository;
import com.elephant.dreamhi.repository.ActorStyleRelationRepository;
import com.elephant.dreamhi.repository.StyleRepository;
import com.elephant.dreamhi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
public class ActorStyleRelationTest {

    @Autowired
    private ActorStyleRelationRepository actorStyleRelationRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorRepository actorRepository;

//    @BeforeEach
//    void StyleInit() {
//        List<Style> styles = new ArrayList<>();
//        Style style = Style.builder()
//                           .count(0)
//                           .description("마른")
//                           .build();
//        Style style2 = Style.builder()
//                            .count(0)
//                            .description("뚱둥한")
//                            .build();
//        Style style3 = Style.builder()
//                            .count(0)
//                            .description("날렵한")
//                            .build();
//        Style style4 = Style.builder()
//                            .count(0)
//                            .description("세련된")
//                            .build();
//        Style style5 = Style.builder()
//                            .count(0)
//                            .description("우울한")
//                            .build();
//        Style style6 = Style.builder()
//                            .count(0)
//                            .description("미친")
//                            .build();
//        Style style7 = Style.builder()
//                            .count(0)
//                            .description("근육맨")
//                            .build();
//        styles.add(style);
//        styles.add(style2);
//        styles.add(style3);
//        styles.add(style4);
//        styles.add(style5);
//        styles.add(style6);
//        styles.add(style7);
//        styleRepository.saveAll(styles);
//
//        User user = User.builder()
//                        .email("test@test.com")
//                        .phone("01012345678")
//                        .password("dummyPassword")
//                        .name("홍길동")
//                        .build();
//        userRepository.save(user);
//
//        ActorProfile actorProfile = ActorProfile.builder()
//                                                .title("안녕하세요 테스트 제목입니다.")
//                                                .user(user)
//                                                .height(178.8)
//                                                .build();
//        actorRepository.save(actorProfile);
//
//        ActorStyleRelation asr1 = ActorStyleRelation.builder()
//                                                   .style(style)
//                                                   .actorProfile(actorProfile).build();
//        ActorStyleRelation asr2 = ActorStyleRelation.builder()
//                                                   .style(style2)
//                                                   .actorProfile(actorProfile).build();
//        ActorStyleRelation asr3 = ActorStyleRelation.builder()
//                                                    .style(style3)
//                                                    .actorProfile(actorProfile).build();
//        ActorStyleRelation asr4 = ActorStyleRelation.builder()
//                                                    .style(style4)
//                                                    .actorProfile(actorProfile).build();
//
//        actorStyleRelationRepository.save(asr1);
//        actorStyleRelationRepository.save(asr2);
//        actorStyleRelationRepository.save(asr3);
//        actorStyleRelationRepository.save(asr4);
//
//        List<ActorStyleRelation> byActorProfileId = actorStyleRelationRepository.findByActorProfileId(1l);
//        byActorProfileId.forEach(
//                a -> System.out.println(a.getStyle().getDescription())
//        );
//    }


    @Test
    @DisplayName("JPQL 이용한 삭제 쿼리 메소드 테스트")
    void deleteAllQueryTest() {
        // Given
        List<Long> deleteStyles = new ArrayList<>();
        deleteStyles.add(1L);
        deleteStyles.add(3L);
        Long actorProfileId = 1L;

        // When
        Integer result = actorStyleRelationRepository.deleteAllInStlyeIdQuery(actorProfileId, deleteStyles);
        System.out.println("result = " + result);

        // Then
        assertThat(result).usingRecursiveComparison()
                           .isEqualTo(2);
    }

    @Test
    @DisplayName("ActorStyleRelation saveAll 테스트")
    void saveAllActorStyleRelationTest() {
        // Given
        Long actorProfileId = 1L;
        List<ActorStyleRelation> newStyleRelations = new ArrayList<>();
        ActorProfile actorProfile = actorRepository.getReferenceById(actorProfileId);
        List<Long> insertStyles = new ArrayList<>();
        insertStyles.add(2L);
        insertStyles.add(6L);
        insertStyles.add(8L);
        insertStyles.forEach(
                isr -> {
                    Style style = styleRepository.getReferenceById(isr);
                    style.addCount();
                    ActorStyleRelation asr = ActorStyleRelation.builder()
                                                               .actorProfile(actorProfile)
                                                               .style(style)
                                                               .build();
                    newStyleRelations.add(asr);
                }
        );

        // When
        actorStyleRelationRepository.saveAll(newStyleRelations);

        List<ActorStyleRelation> byActorProfileId = actorStyleRelationRepository.findByActorProfileId(actorProfileId);

        // Then
        assertThat(byActorProfileId.size()).usingRecursiveComparison()
                .isEqualTo(8);
    }

}
