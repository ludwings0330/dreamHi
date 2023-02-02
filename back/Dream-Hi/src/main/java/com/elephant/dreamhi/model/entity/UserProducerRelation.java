package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.ProducerMemberDto;
import com.elephant.dreamhi.model.statics.ProducerRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user_producer_relation")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserProducerRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @ColumnDefault("'STAFF'")
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'MEMBER'")
    private ProducerRole role;

    public void setProducer(Producer producer) {
        this.producer = producer;
        producer.getUserProducerRelations().add(this);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void changeInfo(ProducerMemberDto info) {
        if (info.getPosition() != null) {
            this.position = info.getPosition();
        }

        if (info.getRole() != null) {
            this.role = info.getRole();
        }
    }

}
