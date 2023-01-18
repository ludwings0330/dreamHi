package com.elephant.dreamhi.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
    private Byte[] description;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer hit;

    // Foreign Key로 매핑하지 않고, 기능 구현의 편의를 위해 process_id만 따로 저장해둔 것
    private Long processId;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @ColumnDefault("now()")
    private LocalDateTime createdDate;

    @Column(name="modified_date", columnDefinition = "TIMESTAMP")
    @NotNull
    @ColumnDefault("now()")
    private LocalDateTime modifiedDate;

}
