package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.FileDto;
import java.util.ArrayList;
import java.util.List;
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
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "notice_file")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NoticeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false)
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

    public static List<NoticeFile> toEntityList(Process process, List<FileDto> fileDtos) {
        List<NoticeFile> noticeFiles = new ArrayList<>();
        fileDtos.forEach(f -> noticeFiles.add(NoticeFile.builder()
                                                        .process(process)
                                                        .url(f.getUrl())
                                                        .savedName(f.getSavedName())
                                                        .originName(f.getOriginName())
                                                        .build())
        );
        return noticeFiles;
    }

}
