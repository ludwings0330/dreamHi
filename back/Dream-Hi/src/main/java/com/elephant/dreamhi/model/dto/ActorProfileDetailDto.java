package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfile;
import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.entity.Filmography;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.model.statics.Gender;
import com.elephant.dreamhi.model.statics.MediaType;
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
    private Integer age;
    private String description;
    private Gender gender;
    private Double height;
    private String title;
    private String email;
    private String name;
    private String phone;
    private String pictureUrl;
    private Boolean isFollow;
    private final List<StyleDto> styles = new ArrayList<>();
    private final List<MediaDto> pictures = new ArrayList<>();
    private final List<MediaDto> videos = new ArrayList<>();
    private final List<FilmographyDto> filmographies = new ArrayList<>();

    public ActorProfileDetailDto(ActorProfile actorProfile, List<Filmography> filmographies, List<ActorProfileMediaFile> mediaFiles,
                                 boolean isFollow) {
        this.id = actorProfile.getId();
        this.age = actorProfile.getAge();
        this.description = actorProfile.getDescription();
        this.gender = actorProfile.getGender();
        this.height = actorProfile.getHeight();
        this.title = actorProfile.getTitle();
        this.email = actorProfile.getUser().getEmail();
        this.name = actorProfile.getUser().getName();
        this.phone = actorProfile.getUser().getPhone();
        this.pictureUrl = actorProfile.getUser().getPicture().getUrl();
        this.isFollow = isFollow;
        for (var actorStyleRelation :
                actorProfile.getActorStyleRelations()) {
            Style style = actorStyleRelation.getStyle();
            final StyleDto dto = StyleDto.builder()
                                         .id(style.getId())
                                         .description(style.getDescription())
                                         .build();
            this.styles.add(dto);
        }

        for (Filmography filmography :
                filmographies) {
            FilmographyDto dto = FilmographyDto.builder()
                                               .id(filmography.getId())
                                               .description(filmography.getDescription())
                                               .title(filmography.getTitle())
                                               .url(filmography.getUrl())
                                               .build();
            this.filmographies.add(dto);
        }

        for (ActorProfileMediaFile mediaFile :
                mediaFiles) {
            final MediaDto dto = MediaDto.builder()
                                         .id(mediaFile.getId())
                                         .url(mediaFile.getUrl())
                                         .build();

            if (mediaFile.getType() == MediaType.PICTURE) {
                this.pictures.add(dto);
            } else if (mediaFile.getType() == MediaType.VIDEO) {
                this.videos.add(dto);
            }
        }
    }

    @Builder
    @Getter
    @Setter
    static class StyleDto {

        private Long id;
        private String description;

    }

    @Builder
    @Getter
    @Setter
    static class MediaDto {

        private Long id;
        private String url;

    }

    @Builder
    @Getter
    @Setter
    static class FilmographyDto {

        private Long id;
        private String description;
        private String title;
        private String url;

    }

}
