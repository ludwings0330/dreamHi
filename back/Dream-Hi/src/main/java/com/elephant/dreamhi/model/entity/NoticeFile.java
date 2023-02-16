package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.FileDto;
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
@Table(name = "notice_file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NoticeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false, updatable = false)
    private Process process;

    // url
    @Column(nullable = false)
    private String url;

    // uuid
    @Column(nullable = false)
    private String savedName;

    // Origin File Name
    @Column(nullable = false)
    private String originName;

    public static NoticeFile toEntity(Process process, FileDto fileDto) {
        return NoticeFile.builder()
                         .process(process)
                         .url(fileDto.getUrl())
                         .savedName(fileDto.getSavedName())
                         .originName(fileDto.getOriginName())
                         .build();
    }

}
