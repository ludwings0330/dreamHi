package com.elephant.dreamhi.model.dto;

import lombok.Data;

@Data
public class FilmographyRequestDto {

    private Long filmographyId;

    private Long actorId;

    private Long producerId;

    private String description;

    private String originName;

    private String savedName;

    private String title;

    private String photoUrl;

}
