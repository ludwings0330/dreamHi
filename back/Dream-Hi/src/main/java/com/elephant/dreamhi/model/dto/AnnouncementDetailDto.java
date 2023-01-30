package com.elephant.dreamhi.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    @NotNull
    private Boolean isFollowed;

    public AnnouncementDetailDto(Long id, String title, Long producerId, String producerName, String payment, String crankPeriod, LocalDateTime endDate, String description, Integer hit,
                                 String pictureUrl, Long followId) {
        this.id = id;
        this.title = title;
        this.payment = payment;
        this.crankPeriod = crankPeriod;
        this.endDate = endDate;
        this.description = description;
        this.hit = hit;
        this.pictureUrl = pictureUrl;

        this.producer = new ProducerAnnouncementDto(producerId, producerName);
        this.isFollowed = (followId != null);
    }

}
