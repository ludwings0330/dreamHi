package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorStyleRelation;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.model.entity.User;
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

    private Long userId;

    private String email;

    private String name;

    private String phone;

    private String picrtureUrl;

    private Long actorProfileId;

    private Integer age;

    private String description;

    private Gender gender;

    private Double height;

    private String title;

    private Boolean visible;
    private final List<StyleDto> styles = new ArrayList<>();

    public ActorProfileDetailDto(ActorProfile actorProfile) {
        User user = actorProfile.getUser();
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.picrtureUrl = user.getPicture() == null ? null : user.getPicture().getUrl();
        this.actorProfileId = actorProfile.getId();
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

}
