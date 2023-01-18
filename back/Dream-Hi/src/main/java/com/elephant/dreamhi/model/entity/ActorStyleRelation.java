package com.elephant.dreamhi.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_style_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActorStyleRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id", nullable = false)
    private ActorProfile actorProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    public void setActorProfile(ActorProfile actorProfile) {
        this.actorProfile = actorProfile;
    }

}
