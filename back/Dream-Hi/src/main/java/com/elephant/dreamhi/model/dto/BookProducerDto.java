package com.elephant.dreamhi.model.dto;

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
    private Long userId;

    public BookProducerDto(Long id, String startTime, String endTime, Boolean reserved, Long userId) {
        super(id, startTime, endTime, reserved);
        this.userId = userId;
    }

}
