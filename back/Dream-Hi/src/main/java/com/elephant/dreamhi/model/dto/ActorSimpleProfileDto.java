package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.statics.Gender;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorSimpleProfileDto {

    private final List<String> styles = new ArrayList<>();
    private Long id;
    private String title;
    private String name;
    private Integer height;
    private String url;
    private Gender gender;
    private Integer age;
    private Boolean isFollow;

    public ActorSimpleProfileDto(ActorProfile profile, Long id) {
        for (var actorStyleRelation :
                profile.getActorStyleRelations()) {
            styles.add(actorStyleRelation.getStyle().getDescription());
        }

        Boolean isFollow = false;
        for (var follower :
                profile.getUser().getFollowActors()) {
            if (follower.getFollower().getId() == id) {
                isFollow = true;
                break;
            }
        }

        this.id = profile.getId();
        this.title = profile.getTitle();
        this.name = profile.getUser().getName();
        this.height = profile.getHeight();
        this.url = profile.getUser().getPicture().getUrl();
        this.gender = profile.getGender();
        this.age = profile.getAge();
        this.isFollow = isFollow;
    }

}
