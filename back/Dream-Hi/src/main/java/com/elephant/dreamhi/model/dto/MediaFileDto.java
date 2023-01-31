package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.statics.MediaType;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaFileDto {

    private Long id;

    private List<MediaDto> videos = new ArrayList<>();

    private List<MediaDto> pictures = new ArrayList<>();


    public MediaFileDto(Long id, List<ActorProfileMediaFile> mediaFiles) {
        this.id = id;

        for (ActorProfileMediaFile mediaFile : mediaFiles) {
            final MediaDto dto = MediaDto.builder()
                                         .id(mediaFile.getId())
                                         .url(mediaFile.getUrl())
                                         .build();

            if (mediaFile.getType() == MediaType.PICTURE) {
                this.pictures.add(dto);
            } else if (mediaFile.getType() == MediaType.VIDEO) {
                this.videos.add(dto);
            }
        }
    }

    @Builder
    @Getter
    @Setter
    static class MediaDto {

        private Long id;
        private String url;

    }

}
