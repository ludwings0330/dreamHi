package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.model.statics.Gender;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
public class ActorProfileDetailDto {

    private Long id;

    private String title;

    private Integer age;

    private Gender gender;

    private Double height;

    private String description;

    private Boolean visible;
    private List<StyleDto> styles = new ArrayList<>();

    public ActorProfileDetailDto(ActorProfile actorProfile) {
        this.id = actorProfile.getId();

        this.title = actorProfile.getTitle();

        this.age = actorProfile.getAge();

        this.gender = actorProfile.getGender();

        this.height = actorProfile.getHeight();

        this.description = actorProfile.getDescription();

        this.visible = actorProfile.getVisible();

        for (ActorStyleRelation actorStyleRelation :
                actorProfile.getActorStyleRelations()) {
            Style style = actorStyleRelation.getStyle();
            final StyleDto dto = StyleDto.builder()
                                         .id(style.getId())
                                         .description(style.getDescription())
                                         .build();
            this.styles.add(dto);
        }
    }

    @Builder
    @Getter
    @Setter
    static class StyleDto {

        private Long id;
        private String description;

    }

}
