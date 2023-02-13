package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import com.elephant.dreamhi.model.statics.VolunteerState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class BookedVolunteerDto {

    private Long userId;

    private String name;

    private Gender gender;

    private Integer age;

    private Integer height;

    private VolunteerState state;

    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startTime;

    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime endTime;

    public BookedVolunteerDto(Long userId, String name, Gender gender, Integer age, Integer height, VolunteerState state, String startTime,
                              String endTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.state = state;
        this.startTime = LocalTime.parse(startTime, dateTimeFormatter);
        this.endTime = LocalTime.parse(endTime, dateTimeFormatter);
    }

}
