package com.elephant.dreamhi.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "user_producer")
public class UserProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @NotNull
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Enumerated(EnumType.STRING)
    @NotNull
    @ColumnDefault("'MEMBER'")
    private ProducerRole role;

}
