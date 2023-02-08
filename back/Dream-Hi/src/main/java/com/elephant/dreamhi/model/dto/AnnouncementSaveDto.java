package com.elephant.dreamhi.model.dto;

import java.util.List;
import javax.validation.constraints.Size;
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
public class AnnouncementSaveDto extends AnnouncementRequestDto {

    @Size.List({
            @Size(min = 1),
            @Size(max = 5)
    })
    private List<CastingSaveDto> castings;

}
