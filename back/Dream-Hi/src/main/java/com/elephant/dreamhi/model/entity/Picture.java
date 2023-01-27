package com.elephant.dreamhi.model.entity;

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

}
