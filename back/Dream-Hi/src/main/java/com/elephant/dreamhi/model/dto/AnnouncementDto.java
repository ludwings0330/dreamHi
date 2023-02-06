package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Picture;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class AnnouncementDto {

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    private Long producerId;

    @Size(max = 25)
    private String payment;

    @Size(max = 30)
    private String crankPeriod;

    @NotNull
    private LocalDateTime endDate;

    private String description;

    @NotNull
    private String pictureUrl;

    public Picture getPictureUrl() {
        return new Picture(pictureUrl, null, null);
    }

}
