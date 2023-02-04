package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Picture;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AnnouncementSaveDto {

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    private ProducerAnnouncementDto producer;

    @Size(max = 25)
    private String payment;

    @Size(max = 30)
    private String crankPeriod;

    @NotNull
    private LocalDateTime endDate;

    private String description;

    @NotNull
    private String pictureUrl;

    @NotEmpty
    private List<CastingSaveDto> castings;

    public Picture getPictureUrl() {
        return new Picture(pictureUrl, null, null);
    }

}
