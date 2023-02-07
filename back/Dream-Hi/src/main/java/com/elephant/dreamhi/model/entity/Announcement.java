package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.AnnouncementRequestDto;
import com.elephant.dreamhi.model.dto.AnnouncementSaveDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "announcement")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    @Embedded
    private Picture picture;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Casting> castings = new ArrayList<>();

    public static Announcement toEntity(AnnouncementSaveDto announcementSaveDto, Producer producer) {
        return Announcement.builder()
                           .producer(producer)
                           .title(announcementSaveDto.getTitle())
                           .payment(announcementSaveDto.getTitle())
                           .crankPeriod(announcementSaveDto.getCrankPeriod())
                           .endDate(announcementSaveDto.getEndDate())
                           .description(announcementSaveDto.getDescription())
                           .picture(announcementSaveDto.getPictureUrl())
                           .build();
    }

    public void changeAnnouncement(AnnouncementRequestDto announcementRequestDto) {
        this.title = announcementRequestDto.getTitle();
        this.payment = announcementRequestDto.getPayment();
        this.crankPeriod = announcementRequestDto.getCrankPeriod();
        this.endDate = announcementRequestDto.getEndDate();
        this.description = announcementRequestDto.getDescription();
        this.picture = announcementRequestDto.getPictureUrl();
    }

}
