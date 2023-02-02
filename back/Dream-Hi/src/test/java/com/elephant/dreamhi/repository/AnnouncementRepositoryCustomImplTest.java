package com.elephant.dreamhi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import com.elephant.dreamhi.exception.NotFoundException;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Follow;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.model.entity.User;
import com.elephant.dreamhi.model.statics.FollowType;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
class AnnouncementRepositoryCustomImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test
    @DisplayName("로그인 유저가 팔로우하지 않은 공고의 상세 정보를 조회하는 경우")
    void findByAnnouncementIdAndFollowerId_1() {
        // given
        User user = createTestUser();
        entityManager.persist(user);

        Producer producer = createTestProducer();
        entityManager.persist(producer);

        Announcement announcement = createTestAnnouncement(producer);
        entityManager.persist(announcement);

        // when
        AnnouncementDetailDto actual = announcementRepository.findByAnnouncementIdAndFollowerId(announcement.getId(), user.getId())
                                                             .orElseThrow(() -> new NotFoundException("DB에 공고가 존재하지 않습니다."));

        entityManager.clear();

        // then
        Announcement expectedAnnouncement = entityManager.find(Announcement.class, announcement.getId());

        assertThat(actual).usingRecursiveComparison()
                          .isEqualTo(AnnouncementDetailDto.entityToDto(expectedAnnouncement));
    }

    @Test
    @DisplayName("로그인 유저가 팔로우한 공고의 상세 정보를 조회하는 경우")
    void findByAnnouncementIdAndFollowerId_2() {
        // given
        User user = createTestUser();
        entityManager.persist(user);

        Producer producer = createTestProducer();
        entityManager.persist(producer);

        Announcement announcement = createTestAnnouncement(producer);
        entityManager.persist(announcement);

        Follow follow = Follow.builder()
                              .follower(user)
                              .announcement(announcement)
                              .type(FollowType.ANNOUNCEMENT)
                              .build();
        entityManager.persist(follow);

        // when
        AnnouncementDetailDto actual = announcementRepository.findByAnnouncementIdAndFollowerId(announcement.getId(), user.getId())
                                                              .orElseThrow(() -> new NotFoundException("DB에 공고가 존재하지 않습니다."));

        entityManager.clear();

        // then
        Announcement expectedAnnouncement = entityManager.find(Announcement.class, announcement.getId());
        Follow expectedFollow = entityManager.find(Follow.class, follow.getId());

        assertThat(actual).usingRecursiveComparison()
                           .isEqualTo(AnnouncementDetailDto.entityToDto(expectedAnnouncement, expectedFollow));
    }

    @Test
    @DisplayName("비로그인 유저가 공고의 상세 정보를 조회하는 경우")
    void findByAnnouncementIdAndFollowerId_3() {
        // given
        Producer producer = createTestProducer();
        entityManager.persist(producer);

        Announcement announcement = createTestAnnouncement(producer);
        entityManager.persist(announcement);

        // when
        AnnouncementDetailDto actual = announcementRepository.findByAnnouncementIdAndFollowerId(announcement.getId(), 0L)
                                                              .orElseThrow(() -> new NotFoundException("DB에 공고가 존재하지 않습니다."));

        entityManager.clear();

        // then
        Announcement expectedAnnouncement = entityManager.find(Announcement.class, announcement.getId());

        assertThat(actual).usingRecursiveComparison()
                           .isEqualTo(AnnouncementDetailDto.entityToDto(expectedAnnouncement));
    }

    private Announcement createTestAnnouncement(Producer producer) {
        return Announcement.builder()
                           .endDate(LocalDateTime.of(2023, 2, 10, 14, 34, 23))
                           .title("더미 공고1")
                           .producer(producer)
                           .build();
    }

    private Producer createTestProducer() {
        return Producer.builder()
                       .name("더미 제작사")
                       .build();
    }

    public User createTestUser() {
        return User.builder()
                   .email("dummy@dream.hi")
                   .name("더미 유저")
                   .password("123kfd")
                   .phone("01012346482")
                   .build();
    }

}