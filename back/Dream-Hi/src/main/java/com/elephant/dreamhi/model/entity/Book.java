package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.BookPeriodSaveDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "book")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false, updatable = false)
    private Process process;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Column(columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean reserved;

    public static List<Book> toEntityList(Process process, BookPeriodSaveDto bookPeriodSaveDto, long timeTaken) {
        LocalDateTime startDateTime = bookPeriodSaveDto.getStartDate();
        LocalDateTime endDateTime = bookPeriodSaveDto.getEndDate();
        return makeBooks(process, startDateTime, endDateTime, timeTaken);
    }

    private static List<Book> makeBooks(Process process, LocalDateTime startDateTime, LocalDateTime endDateTime, long timeTaken) {
        List<Book> books = new ArrayList<>();
        int startHourOfDay = startDateTime.getHour();
        int endHourOfDay = endDateTime.getHour();
        int startMinuteOfDay = startDateTime.getMinute();
        int endMinuteOfDay = endDateTime.getMinute();
        LocalDateTime curDateTime = startDateTime;

        while (!curDateTime.isAfter(endDateTime)) {
            books.add(
                    Book.builder()
                        .process(process)
                        .startTime(curDateTime)
                        .endTime(curDateTime.plusMinutes(timeTaken))
                        .build()
            );

            // 현재 시각이 그날의 예약 가능한 시각의 마지막인 경우 다음 날로 이동
            if (curDateTime.getHour() == endHourOfDay && curDateTime.getMinute() == endMinuteOfDay) {
                curDateTime = curDateTime.plusDays(1L).withHour(startHourOfDay).withMinute(startMinuteOfDay);
                continue;
            }

            curDateTime = curDateTime.plusMinutes(timeTaken);
        }

        return books;
    }

    public void reverse() {
        if (reserved) {
            throw new IllegalStateException("이미 예약된 화상 오디션입니다.");
        }

        this.reserved = true;
    }

}
