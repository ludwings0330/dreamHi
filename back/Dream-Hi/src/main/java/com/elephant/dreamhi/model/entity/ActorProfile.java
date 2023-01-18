package com.elephant.dreamhi.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "actor_profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 7, nullable = false)
    private String name;

    @Column(length = 11)
    private String phone;

    @Column(length = 20, nullable = false)
    private String title;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double height;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean visible;

    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActorStyleRelation> actorStyleRelations = new ArrayList<>();

    public void addActorStyle(ActorStyleRelation actorStyleRelation) {
        this.actorStyleRelations.add(actorStyleRelation);
        actorStyleRelation.setActorProfile(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

}
