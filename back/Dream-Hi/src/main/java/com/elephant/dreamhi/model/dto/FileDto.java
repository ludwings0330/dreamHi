package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.NoticeFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {

    private String originName;
    private String savedName;
    private String url;

    public static FileDto toDto(NoticeFile file) {
        return FileDto.builder()
                      .originName(file.getOriginName())
                      .savedName(file.getSavedName())
                      .url(file.getUrl())
                      .build();
    }

}
