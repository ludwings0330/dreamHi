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
@Table(name = "volunteer_casting")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class VolunteerCasting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(targetEntity = Volunteer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @ManyToOne(targetEntity = Casting.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "casting_id", nullable = false)
    private Casting casting;

}
