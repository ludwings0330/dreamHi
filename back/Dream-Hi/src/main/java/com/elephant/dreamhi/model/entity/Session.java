package com.elephant.dreamhi.model.entity;

import javax.persistence.Column;
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
@Table(name = "session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id", nullable = false, updatable = false)
    private Announcement announcement;

    @Column(nullable = false, unique = true, updatable = false)
    private String sessionId;

    public Session(Announcement announcement, String sessionId) {
        this.announcement = announcement;
        this.sessionId = sessionId;
    }

}
