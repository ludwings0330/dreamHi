package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.statics.FollowType;
import java.time.LocalDateTime;
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
@Table(name = "follow")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FollowType type;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @ColumnDefault("now()")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private User actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    /**
     * type을 보고 알맞은 Relation 객체 넣어주기 메소드
     *
     * @param type
     * @param relation : 조회한 proxy 객체
     */
    public void setRelationObject(FollowType type, Object relation) {
        switch (type) {
            case ACTOR:
                this.actor = (User) relation;
                break;
            case ANNOUNCEMENT:
                this.announcement = (Announcement) relation;
                break;
            case PRODUCER:
                this.producer = (Producer) relation;
                break;
            default:
                break;
        }
    }

    /**
     * Follower relation 설정 메소드
     *
     * @param follower
     */
    public void setFollower(User follower) {
        this.follower = follower;
    }

}
