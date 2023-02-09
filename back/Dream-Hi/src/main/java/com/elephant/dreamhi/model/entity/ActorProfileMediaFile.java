package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.statics.MediaType;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_profile_media_file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ActorProfileMediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type;

    // url
    @Column(nullable = false)
    private String url;

    // uuid
    @Column(nullable = false)
    private String savedName;

    // Origin File Name
    @Column(nullable = false)
    private String originName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_profile_id")
    private ActorProfile actorProfile;

    // 편의메소드
    public void changeActorProfile(ActorProfile actorProfile) {
        if (this.actorProfile != null) {
            this.actorProfile.getActorProfileMediaFiles().remove(this);
        }
        this.actorProfile = actorProfile;
        actorProfile.getActorProfileMediaFiles().add(this);
    }

}
