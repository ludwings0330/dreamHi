package com.elephant.dreamhi.model.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
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
public class AnnouncementUpdateDto extends AnnouncementRequestDto {

    @NotNull
    private Long id;

    @NotEmpty
    private List<CastingUpdateDto> castings;

}
