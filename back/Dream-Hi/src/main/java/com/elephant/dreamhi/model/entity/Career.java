package com.elephant.dreamhi.model.entity;

import java.util.Date;
import javax.persistence.Column;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id", nullable = false)
    private ActorProfile actorProfile;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String description;

    public void setActorProfile(ActorProfile actorProfile) {
        this.actorProfile = actorProfile;
    }

}
