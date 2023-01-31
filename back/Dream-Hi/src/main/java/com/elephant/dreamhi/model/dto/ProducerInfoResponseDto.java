package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Producer;
import lombok.Data;

@Data
public class ProducerInfoResponseDto {

    private String name;
    private String pictureUrl;
    private String description;
    private Boolean isFollow = false;

    public ProducerInfoResponseDto(String name, String pictureUrl, String description) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.description = description;
    }

    public ProducerInfoResponseDto(Producer producer) {
        this(producer.getName(),
             producer.getPicture().getUrl(),
             producer.getDescription());
    }

}
