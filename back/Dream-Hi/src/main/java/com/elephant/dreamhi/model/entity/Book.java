package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.AuditionCreateRequestDto;
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
    @JoinColumn(name = "process_id")
    private Process process;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime startTime;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean reserved;

    public static List<Book> toEntityList(Process process, AuditionCreateRequestDto auditionCreateRequestDto, long period) {
        LocalDateTime startDate = auditionCreateRequestDto.getStartDate();
        LocalDateTime endDate = auditionCreateRequestDto.getEndDate();
        List<Book> books = makeBooks(process, period, endDate, auditionCreateRequestDto.getStartDate(), startDate.getHour(), startDate.getMinute(),
                                     endDate.getHour(),
                                     endDate.getMinute());
        return books;
    }

    private static List<Book> makeBooks(Process process, long period, LocalDateTime endDate, LocalDateTime curDate, int startHour, int startMinute,
                                        int endHour, int endMinute) {
        List<Book> books = new ArrayList<>();
        while (!curDate.isAfter(endDate)) {
            books.add(Book.builder()
                          .process(process)
                          .startTime(curDate)
                          .endTime(curDate.plusMinutes(period))
                          .build());
            if (curDate.getHour() == endHour && curDate.getMinute() == endMinute) {
                curDate = curDate.plusDays(1).withHour(startHour).withMinute(startMinute);
                continue;
            }
            curDate = curDate.plusMinutes(period);
        }
        return books;
    }

}
