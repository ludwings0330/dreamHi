package com.elephant.dreamhi.model.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "career")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(targetEntity = ActorProfile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id")
    @NotNull
    private ActorProfile actorProfile;

    @NotNull
    private Date startDate;

    private Date endDate;

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 20)
    private String description;

}
