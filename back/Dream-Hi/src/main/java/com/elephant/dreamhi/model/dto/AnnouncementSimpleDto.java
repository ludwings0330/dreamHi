package com.elephant.dreamhi.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AnnouncementSimpleDto {

    private Long id;

    private String title;

    private String producerName;

    private ProcessStageDto state;

    private Boolean isFollow;

    private LocalDateTime createDate;

    private Integer hit;

    private List<CastingSimpleDto> castings;

    public AnnouncementSimpleDto(Long id, String title, String producerName, Boolean isFollow, LocalDateTime createDate, Integer hit, List<CastingSimpleDto> castings) {
        this.id = id;
        this.title = title;
        this.producerName = producerName;
        this.isFollow = isFollow;
        this.createDate = createDate;
        this.hit = hit;
        this.castings = castings;
    }

    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    public void setState(ProcessStageDto state) {
        this.state = state;
    }

}
