package com.elephant.dreamhi.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
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

    public AnnouncementDetailDto(Long id, String title, Long producerId, String producerName, String payment, String crankPeriod,
                                 LocalDateTime endDate, String description, Integer hit,
                                 String pictureUrl, Boolean isFollowed) {
        this.id = id;
        this.title = title;
        this.producer = new ProducerAnnouncementDto(producerId, producerName);
        this.payment = payment;
        this.crankPeriod = crankPeriod;
        this.endDate = endDate;
        this.description = description;
        this.hit = hit;
        this.pictureUrl = pictureUrl;
        this.isFollowed = isFollowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnnouncementDetailDto that = (AnnouncementDetailDto) o;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(
                getProducer(), that.getProducer()) && Objects.equals(getPayment(), that.getPayment()) && Objects.equals(
                getCrankPeriod(), that.getCrankPeriod()) && Objects.equals(getEndDate(), that.getEndDate()) && Objects.equals(
                getDescription(), that.getDescription()) && Objects.equals(getHit(), that.getHit()) && Objects.equals(getPictureUrl(),
                                                                                                                      that.getPictureUrl())
                && Objects.equals(getIsFollowed(), that.getIsFollowed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getProducer(), getPayment(), getCrankPeriod(), getEndDate(), getDescription(), getHit(),
                            getPictureUrl(),
                            getIsFollowed());
    }

}
