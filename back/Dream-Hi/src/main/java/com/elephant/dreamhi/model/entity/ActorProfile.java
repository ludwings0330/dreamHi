package com.elephant.dreamhi.model.entity;

import java.util.ArrayList;
import java.util.List;
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
import javax.validation.constraints.NotNull;
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

    @OneToOne
    @JoinColumn(name = "user_id")

    @NotNull
    private User user;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Double height;

    @NotNull
    @ColumnDefault("1")
    private Boolean visible;

    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY)
    private List<ActorFigure> actorFigures = new ArrayList<>();

    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY)
    private List<Career> careers = new ArrayList<>();

    public void addActorFigure(ActorFigure actorFigure) {
        this.actorFigures.add(actorFigure);
        actorFigure.setActorProfile(this);
    }

    public void addCareer(Career career) {
        this.careers.add(career);
        career.setActorProfile(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

}
