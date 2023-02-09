package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.ActorProfileRequestDto;
import com.elephant.dreamhi.model.statics.Gender;
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
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "actor_profile")
@DynamicInsert
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorProfile {

    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ActorProfileMediaFile> actorProfileMediaFiles = new ArrayList<>();
    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Filmography> filmographies = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    @Column(length = 40, nullable = false)
    private String title;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer height;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean visible;
    @OneToMany(mappedBy = "actorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActorStyleRelation> actorStyleRelations = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
    }

    public void changeVisible() {
        this.visible = !this.visible;
    }

    public void changeActorProfileInfo(ActorProfileRequestDto actorProfileRequestDto) {
        this.title = actorProfileRequestDto.getTitle();
        this.gender = actorProfileRequestDto.getGender();
        this.age = actorProfileRequestDto.getAge();
        this.height = actorProfileRequestDto.getHeight();
        this.description = actorProfileRequestDto.getDescription();
    }

    // ActorStyle 추가 편의 메소드
    public void addActorStyle(ActorStyleRelation actorStyleRelation) {
        this.actorStyleRelations.add(actorStyleRelation);
        if (actorStyleRelation.getActorProfile() != this) {
            actorStyleRelation.setActorProfile(this);
        }
    }

    // actor profile picture 편의 메소드

    // filmography 편의 메소드

}
