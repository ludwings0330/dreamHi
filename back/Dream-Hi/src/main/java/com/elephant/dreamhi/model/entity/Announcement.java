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
    @JoinColumn(name = "producer_id")
    @NotNull
    private Producer producer;

    @Size(max = 20)
    @NotNull
    private String title;

    @Size(max = 25)
    @NotNull
    @ColumnDefault("'협의 후 결정'")
    private String payment;

    @Size(max = 30)
    private String crankPeriod;

    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime endDate;

    @Lob
    private Byte[] description;

    @NotNull
    @ColumnDefault("0")
    private Integer hit;

    // Foreign Key로 매핑하지 않고, 기능 구현의 편의를 위해 process_id만 따로 저장해둔 것
    private Long processId;

    @Column(name="create_date", columnDefinition = "TIMESTAMP")
    @NotNull
    @ColumnDefault("now()")
    private LocalDateTime createdDate;

    @Column(name="modified_date", columnDefinition = "TIMESTAMP")
    @NotNull
    @ColumnDefault("now()")
    private LocalDateTime modifiedDate;

}
