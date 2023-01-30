package com.elephant.dreamhi.model.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProducerAnnouncementDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}
