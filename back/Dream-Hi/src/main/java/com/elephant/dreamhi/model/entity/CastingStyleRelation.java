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
@Table(name = "casting_style_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CastingStyleRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casting_id", nullable = false)
    private Casting casting;

    public void setStyle(Style style) {
        this.style = style;
    }

    public void setCasting(Casting casting) {
        if (this.casting != null) {
            this.casting.getCastingStyleRelations().remove(this);
        }

        this.casting = casting;
        if (!this.casting.getCastingStyleRelations().contains(this)) {
            casting.addCastingStyleRelation(this);
        }
    }

}
