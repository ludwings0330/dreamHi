package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.statics.ProducerRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @ManyToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserProducerRelation)) {
            return false;
        }

        UserProducerRelation that = (UserProducerRelation) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
