package com.elephant.dreamhi.auditionTimeTest;

import com.elephant.dreamhi.config.QueryDslTestConfig;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslTestConfig.class)
public class AuditionCreateTimeTest {

    private final long PERIOD = 30;

    @Test
    @DisplayName("LocalDateTime 이용한 createDto 만들기 테스트")
    public void createAuditionTest() {
        LocalDateTime startDate = LocalDateTime.of(2023, 02, 13, 9, 00);
        LocalDateTime endDate = LocalDateTime.of(2023, 03, 01, 18, 00);
        LocalDateTime temp = startDate;
        int startHour = startDate.getHour();
        int startMinute = startDate.getMinute();
        int endHour = endDate.getHour();
        int endMinute = endDate.getMinute();
        List<LocalDateTime> auditions = new ArrayList<>();
        while (!temp.isAfter(endDate)) {
            auditions.add(temp);
            if (temp.getHour() == endHour && temp.getMinute() == endMinute) {
                temp = temp.plusDays(1).withHour(startHour).withMinute(startMinute);
                continue;
            }
            temp = temp.plusMinutes(PERIOD);
        }
        System.out.println(auditions);

    }

}
