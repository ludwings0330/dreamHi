package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.AnnouncementDetailDto;
import com.elephant.dreamhi.model.dto.AnnouncementDetailDto.AnnouncementDetailDtoBuilder;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "announcement")
@DynamicInsert
@NoArgsConstructor
@Getter
//@Builder
public class Announcement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 25, nullable = false)
    @ColumnDefault("'협의 후 결정'")
    private String payment;

    @Column(length = 30)
    private String crankPeriod;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime endDate;

    @Lob
    private String description;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer hit;

    // Foreign Key로 매핑하지 않고, 기능 구현의 편의를 위해 process_id만 따로 저장해둔 것
    private Long processId;

//    @OneToMany(mappedBy = "announcement", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Casting> castings = new ArrayList<>();

    @Embedded
    private Picture picture;

    @Builder
    public Announcement(Long id, Producer producer, String title, String payment, String crankPeriod, LocalDateTime endDate, String description,
                        Integer hit, Long processId) {
        this.id = id;
        this.producer = producer;
        this.title = title;
        this.payment = payment;
        this.crankPeriod = crankPeriod;
        this.endDate = endDate;
        this.description = description;
        this.hit = hit;
        this.processId = processId;
    }

    // 편의 메소드

    /**
     * @param follow 현재 로그인한 유저가 이 공고를 팔로우 했는지에 대한 정보
     * @return 공고 상세 DTO
     */
    public AnnouncementDetailDto toAnnouncementDetailDto(Follow follow) {
        AnnouncementDetailDtoBuilder dtoBuilder = AnnouncementDetailDto.builder()
                                                                       .id(this.id)
                                                                       .title(this.title)
                                                                       .producer(this.producer.toProducerAnnouncementDto())
                                                                       .payment(this.payment)
                                                                       .crankPeriod(this.crankPeriod)
                                                                       .endDate(this.endDate)
                                                                       .description(this.description)
                                                                       .hit(this.hit)
                                                                       .isFollowed(Boolean.FALSE);

        if (this.picture != null) {
            dtoBuilder.pictureUrl(this.picture.getUrl());
        }

        if (follow != null) {
            dtoBuilder.isFollowed(Boolean.TRUE);
        }

        return dtoBuilder.build();
    }

}
