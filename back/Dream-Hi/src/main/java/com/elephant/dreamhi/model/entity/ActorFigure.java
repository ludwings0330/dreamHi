package com.elephant.dreamhi.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_figure")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorFigure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ActorProfile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id")
    @NotNull
    private ActorProfile actorProfile;

    @ManyToOne(targetEntity = Figure.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "figure_id")
    @NotNull
    private Figure figure;


}
