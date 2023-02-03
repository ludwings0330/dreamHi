package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Announcement;
import com.elephant.dreamhi.model.entity.Follow;
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
    private Boolean isFollow;

    public AnnouncementDetailDto(Long id, String title, Long producerId, String producerName, String payment, String crankPeriod,
                                 LocalDateTime endDate, String description, Integer hit, String pictureUrl, Boolean isFollow) {
        this.id = id;
        this.title = title;
        this.producer = new ProducerAnnouncementDto(producerId, producerName);
        this.payment = payment;
        this.crankPeriod = crankPeriod;
        this.endDate = endDate;
        this.description = description;
        this.hit = hit;
        this.pictureUrl = pictureUrl;
        this.isFollow = isFollow;
    }

    /**
     * @param announcement DTO로 변경하려고 하는 공고 엔티티
     * @param follow       현재 로그인한 유저가 공고를 팔로우 했는지에 대한 정보
     * @return 공고 상세 DTO
     */
    public static AnnouncementDetailDto toDto(Announcement announcement, Follow follow) {
        AnnouncementDetailDtoBuilder dtoBuilder = AnnouncementDetailDto.builder()
                                                                       .id(announcement.getId())
                                                                       .title(announcement.getTitle())
                                                                       .producer(ProducerAnnouncementDto.entityToDto(announcement.getProducer()))
                                                                       .payment(announcement.getPayment())
                                                                       .crankPeriod(announcement.getCrankPeriod())
                                                                       .endDate(announcement.getEndDate())
                                                                       .description(announcement.getDescription())
                                                                       .hit(announcement.getHit())
                                                                       .isFollow(Boolean.FALSE);

        if (announcement.getPicture() != null) {
            dtoBuilder.pictureUrl(announcement.getPicture().getUrl());
        }

        if (follow != null) {
            dtoBuilder.isFollow(Boolean.TRUE);
        }

        return dtoBuilder.build();
    }

    /**
     * @param announcement DTO로 변경하려고 하는 공고 엔티티
     * @return 공고 상세 DTO
     */
    public static AnnouncementDetailDto toDto(Announcement announcement) {
        return toDto(announcement, null);
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
                && Objects.equals(getIsFollow(), that.getIsFollow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getProducer(), getPayment(), getCrankPeriod(), getEndDate(), getDescription(), getHit(),
                            getPictureUrl(),
                            getIsFollow());
    }

}
