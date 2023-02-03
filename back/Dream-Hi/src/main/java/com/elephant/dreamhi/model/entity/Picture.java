package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.PictureDto;
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
    @Column(name = "picture_saved_name", nullable = true, length = 45)
    private String savedName;

    // Origin File Name
    @Column(name = "picture_origin_name", nullable = true)
    private String originName;

    public void updateUrl(String pictureUrl) {
        this.url = pictureUrl;
    }

    public void updatePicture(PictureDto pictureDto) {
        this.url = pictureDto.getUrl();
        this.savedName = pictureDto.getSavedName();
        this.originName = pictureDto.getOriginName();
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
