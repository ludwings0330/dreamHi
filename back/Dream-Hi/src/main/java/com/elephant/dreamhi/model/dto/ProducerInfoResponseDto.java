package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Producer;
import lombok.Data;

@Data
public class ProducerInfoResponseDto {

    private String name;
    private String pictureUrl;
    private String description;
    private Boolean isFollow = false;

    public void setInfo(Producer producer) {
        this.name = producer.getName();
        this.pictureUrl = producer.getPicture().getUrl();
        this.description = producer.getDescription();
    }

}
