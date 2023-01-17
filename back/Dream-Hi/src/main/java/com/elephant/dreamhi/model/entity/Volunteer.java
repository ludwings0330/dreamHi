package com.elephant.dreamhi.model.entity;

import javax.persistence.Entity;
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

@Entity
@Table(
        name = "volunteer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueUserApplication",
                        columnNames = { "user", "announcement" }
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    @NotNull
    private Announcement announcement;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    private VolunteerState state;

}
