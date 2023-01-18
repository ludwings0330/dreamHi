package com.elephant.dreamhi.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(
        name = "volunteer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueUserApplication",
                        columnNames = { "user_id", "announcement_id" }
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id", nullable = false)
    private Announcement announcement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private Process process;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'NONE'")
    private VolunteerState state;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP", nullable = false)
    @ColumnDefault("now()")
    private LocalDateTime createdDate;

}
