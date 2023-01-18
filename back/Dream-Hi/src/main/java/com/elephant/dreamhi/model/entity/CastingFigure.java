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
@Table(name = "casting_figure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CastingFigure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "figure_id", nullable = false)
    private Figure figure;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casting_id", nullable = false)
    private Casting casting;

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void setCasting(Casting casting) {
        if (this.casting != null) {
            this.casting.getCastingFigures().remove(this);
        }

        this.casting = casting;
        if (!this.casting.getCastingFigures().contains(this)) {
            casting.addCastingFigure(this);
        }
    }

}
