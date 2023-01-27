package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.ProducerUpdateRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Lob
    private String description;

    @Embedded
    private Picture picture;

    @Builder.Default
    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProducerRelation> userProducerRelations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Filmography> filmographies = new ArrayList<>();

    public void updateInfo(ProducerUpdateRequestDto producerDto) {
        if (producerDto.getName() != null) {
            this.name = producerDto.getName();
        }
        if (producerDto.getDescription() != null) {
            this.description = producerDto.getDescription();
        }
        if (producerDto.getPictureUrl() != null) {
            this.picture.updateUrl(producerDto.getPictureUrl());
        }
    }

    // filmography 편의 메소드

}
