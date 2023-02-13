package com.elephant.dreamhi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
@ToString(callSuper = true)
public class BookProducerDto extends BookResponseDto {

    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Long userId;

    public BookProducerDto(Long id, String startTime, String endTime, Boolean reserved, Long userId) {
        super(id, startTime, endTime, reserved);
        this.userId = userId;
    }

}
