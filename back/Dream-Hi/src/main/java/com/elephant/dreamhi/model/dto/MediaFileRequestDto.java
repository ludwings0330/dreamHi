package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.ActorProfileMediaFile;
import com.elephant.dreamhi.model.statics.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaFileRequestDto {

    private String originName;

    private String savedName;

    private MediaType type;

    private String url;

    public ActorProfileMediaFile toEntity() {
        return ActorProfileMediaFile.builder()
                                    .originName(originName)
                                    .savedName(savedName)
                                    .type(type)
                                    .url(url)
                                    .build();
    }

}
