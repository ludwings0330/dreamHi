package com.elephant.dreamhi.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDetailDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private ProducerAnnouncementDto producer;

    @NotNull
    private String payment;

    private String crankPeriod;

    @NotNull
    private LocalDateTime endDate;

    private String description;

    @NotNull
    private Integer hit;

    private String pictureUrl;

    private Boolean isFollowed;

}
