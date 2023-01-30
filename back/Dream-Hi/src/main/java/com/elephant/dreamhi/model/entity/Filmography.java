package com.elephant.dreamhi.model.entity;

import javax.persistence.Column;
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
@Table(name = "filmography")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Filmography {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // url
    @Column(nullable = false)
    private String url;

    // uuid
    @Column(nullable = false, length = 45)
    private String savedName;

    // Origin File Name
    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id")
    private ActorProfile actorProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    //    연관관계 메서드 추가
    public void setActorProfile(ActorProfile actorProfile) {
        this.actorProfile = actorProfile;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

}
