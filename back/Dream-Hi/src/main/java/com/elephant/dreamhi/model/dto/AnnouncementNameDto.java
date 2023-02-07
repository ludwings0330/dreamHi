package com.elephant.dreamhi.model.dto;

import javax.validation.constraints.NotNull;
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
public class AnnouncementNameDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String producerName;

}
