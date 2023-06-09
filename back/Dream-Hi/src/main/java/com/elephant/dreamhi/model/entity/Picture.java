package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.FileDto;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Picture {

    // url
    @Column(name = "picture_url", nullable = true)
    private String url;

    // uuid
    @Column(name = "picture_saved_name", nullable = true)
    private String savedName;

    // Origin File Name
    @Column(name = "picture_origin_name", nullable = true)
    private String originName;

    public void updateUrl(String pictureUrl) {
        this.url = pictureUrl;
    }

    public void updatePicture(FileDto fileDto) {
        this.url = fileDto.getUrl();
        this.savedName = fileDto.getSavedName();
        this.originName = fileDto.getOriginName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Picture picture = (Picture) o;

        return Objects.equals(getUrl(), picture.getUrl()) && Objects.equals(getSavedName(), picture.getSavedName())
                && Objects.equals(getOriginName(), picture.getOriginName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl(), getSavedName(), getOriginName());
    }

}
