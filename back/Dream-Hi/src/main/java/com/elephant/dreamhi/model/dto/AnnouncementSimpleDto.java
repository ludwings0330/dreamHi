package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Casting;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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

    private Boolean isFollow = Boolean.FALSE;

    private LocalDateTime createDate;

    private Integer hit;

    private List<CastingSimpleDto> castings;

    public AnnouncementSimpleDto(Long id, String title, String producerName, LocalDateTime createDate, Integer hit, List<Casting> castings) {
        this.id = id;
        this.title = title;
        this.producerName = producerName;
        this.createDate = createDate;
        this.hit = hit;
        this.castings = castings.stream()
                                .map(CastingSimpleDto::toDto)
                                .collect(Collectors.toList());
    }

    public void setIsFollow(Boolean isFollow) {
        this.isFollow = isFollow;
    }

    public void setState(ProcessStageDto state) {
        this.state = state;
    }

}
