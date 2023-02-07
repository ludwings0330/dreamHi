package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.ProcessSaveDto;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.StageName;
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
@Table(name = "process")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id", nullable = false)
    private Announcement announcement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'RECRUITING'")
    private ProcessState state;

    @Enumerated(EnumType.STRING)
    private StageName stage;

    public static Process getInstanceForRecruiting(Announcement announcement) {
        return Process.builder()
                      .announcement(announcement)
                      .state(ProcessState.RECRUITING)
                      .build();
    }

    public static Process getInstanceForInProgress(Announcement announcement) {
        return Process.builder()
                      .announcement(announcement)
                      .state(ProcessState.IN_PROGRESS)
                      .build();
    }

    public static Process toEntity(Announcement announcement, ProcessSaveDto processSaveDto) {
        return Process.builder()
                      .announcement(announcement)
                      .stage(processSaveDto.getStage())
                      .state(processSaveDto.getState())
                      .build();
    }

}
