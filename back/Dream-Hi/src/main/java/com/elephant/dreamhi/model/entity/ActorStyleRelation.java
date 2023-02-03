package com.elephant.dreamhi.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_style_relation",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueUserStyleTage",
                        columnNames = { "actor_profile_id", "style_id" }
                )
        })
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
        if (this.actorProfile != null) {
            this.actorProfile.getActorStyleRelations().remove(this);
        }
        this.actorProfile = actorProfile;
        // 이부분이 없으면 무한 루프에 걸린다.
        if (!actorProfile.getActorStyleRelations().contains(this)) {
            actorProfile.addActorStyle(this);
        }
    }

}
