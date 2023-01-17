package com.elephant.dreamhi.model.entity;

import java.time.LocalDate;
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

    @NotNull
    @ColumnDefault("now()")
    @Column(name="create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @NotNull
    @ColumnDefault("now()")
    @Column(name="modified_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedDate;

    @NotNull
    @Column(name = "end_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @Lob
    private Byte[] description;

    @NotNull
    @ColumnDefault("0")
    private int hit;

    private Long processId;

}
