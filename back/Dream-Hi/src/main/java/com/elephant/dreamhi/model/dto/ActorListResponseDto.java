package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorListResponseDto {

    private Long userId;
    private String name;
    private String pictureUrl;
    private Long actorProfileId;
    private String title;
    private Gender gender;
    private Integer age;
    private Integer height;
    private List<StyleDto> styles;
    @NotNull
    private Boolean isFollow;

}
