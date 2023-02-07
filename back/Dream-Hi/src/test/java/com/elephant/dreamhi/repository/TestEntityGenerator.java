package com.elephant.dreamhi.repository;

import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Casting;
import com.elephant.dreamhi.model.entity.CastingStyleRelation;
import com.elephant.dreamhi.model.entity.Producer;
import com.elephant.dreamhi.model.entity.Style;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class TestEntityGenerator {

    public static Producer createTestProducer(int index) {
        return Producer.builder()
                       .name("더미 제작사" + index)
                       .build();
    }

    public static Producer createTestProducer() {
        return createTestProducer(1);
    }

    public static Announcement createTestAnnouncement(Producer producer, int index) {
        return Announcement.builder()
                           .endDate(LocalDate.of(2023, 2, 10))
                           .title("더미 공고" + index)
                           .producer(producer)
                           .build();
    }

    public static Announcement createTestAnnouncement(Producer producer) {
        return createTestAnnouncement(producer, 1);
    }

    public static Casting createTestCasting(Announcement announcement, int index) {
        return Casting.builder()
                      .name("더미 배역" + index)
                      .announcement(announcement)
                      .description("더미 배역" + index + " 역할을 수행합니다.")
                      .headcount(1)
                      .build();
    }

    public static Casting createTestCasting(Announcement announcement) {
        return createTestCasting(announcement, 1);
    }

    /**
     * @param fromIndex inclusive index
     * @param toIndex   exclusive index
     * @return 더미 스타일의 목록을 반환합니다.
     */
    public static List<Style> createTestStyles(int fromIndex, int toIndex) {
        List<Style> styles = new ArrayList<>();

        for (int i = fromIndex;  i < toIndex; i++) {
            styles.add(Style.builder()
                            .description("더미 스타일" + i)
                            .build());
        }

        return styles;
    }

    public static List<CastingStyleRelation> createTestCastingStyleRelations(Casting castings, List<Style> styles) {
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
