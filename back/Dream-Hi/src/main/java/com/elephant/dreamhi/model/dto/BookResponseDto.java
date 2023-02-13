package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalTime;
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
    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime endTime;

    @NotNull
    private Boolean reserved;

    public BookResponseDto(Long id, String startTime, String endTime, Boolean reserved) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss").localizedBy(Locale.KOREA);
        this.id = id;
        this.startTime = LocalTime.parse(startTime, dateTimeFormatter);
        this.endTime = LocalTime.parse(endTime, dateTimeFormatter);
        this.reserved = reserved;
    }

    public static BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                              .id(book.getId())
                              .startTime(book.getStartTime().toLocalTime())
                              .endTime(book.getEndTime().toLocalTime())
                              .reserved(book.getReserved())
                              .build();
    }

}
