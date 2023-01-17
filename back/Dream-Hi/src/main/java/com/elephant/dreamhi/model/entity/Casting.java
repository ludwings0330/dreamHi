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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "casting")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Casting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    @NotNull
    private Announcement announcement;

    @Size(max = 20)
    @NotNull
    private String description;

    @NotNull
    private Integer headcount;

    private Integer minHeight;

    private Integer maxHeight;

    private Integer minAge;

    private Integer maxAge;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "casting", fetch = FetchType.LAZY)
    private List<CastingFigure> castingFigures = new ArrayList<>();

    public void addCastingFigure(CastingFigure castingFigure) {
        this.castingFigures.add(castingFigure);
        if (castingFigure.getCasting() != this) {
            castingFigure.setCasting(this);
        }
    }

}
