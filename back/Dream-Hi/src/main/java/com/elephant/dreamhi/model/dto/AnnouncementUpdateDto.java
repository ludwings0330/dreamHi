package com.elephant.dreamhi.model.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class AnnouncementUpdateDto extends AnnouncementDto {

    @NotNull
    private Long id;

    @NotEmpty
    private List<CastingUpdateDto> castings;

}
