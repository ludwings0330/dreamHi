package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
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
    private String[] styles;
    private Boolean isFollow;

    private List<MediaDto> photos = new ArrayList<>();
    private List<MediaDto> videos = new ArrayList<>();
    private List<FilmographyDto> filmographies = new ArrayList<>();

    class MediaDto {

    }

    class FilmographyDto {

    }

}
