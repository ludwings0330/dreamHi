package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@ToString
public class BookResponseDto {

    @NotNull
    private Long id;

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDateTime;

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDateTime;

    @NotNull
    private Boolean reserved;

    public BookResponseDto(Long id, String startTime, String endTime, Boolean reserved) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").localizedBy(Locale.KOREA);
        this.id = id;
        this.startDateTime = LocalDateTime.parse(startTime, dateTimeFormatter);
        this.endDateTime = LocalDateTime.parse(endTime, dateTimeFormatter);
        this.reserved = reserved;
    }

    public static BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                              .id(book.getId())
                              .startDateTime(book.getStartTime())
                              .endDateTime(book.getEndTime())
                              .reserved(book.getReserved())
                              .build();
    }

}
