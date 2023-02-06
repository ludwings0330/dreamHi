package com.elephant.dreamhi.model.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class AnnouncementSaveDto extends AnnouncementDto {

    @Size.List({
            @Size(min = 1),
            @Size(max = 5)
    })
    private List<CastingSaveDto> castings;

}
