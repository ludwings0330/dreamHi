package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ActorSimpleProfileDto {

    private Long id;
    private String title;
    private String name;
    private Integer height;
    private String url;
    private Gender gender;
    private Integer age;
    private String[] styles;
    private Boolean isFollow;

    @QueryProjection
    public ActorSimpleProfileDto(Long id, String title, String name, Integer height, String url, Gender gender, Integer age, String[] tags,
                                 Boolean isFollow) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.height = height;
        this.url = url;
        this.gender = gender;
        this.age = age;
        this.styles = tags;
        this.isFollow = isFollow;
    }

}
