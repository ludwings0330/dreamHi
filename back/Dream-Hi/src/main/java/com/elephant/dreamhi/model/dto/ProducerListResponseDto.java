package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Producer;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerListResponseDto {

    private Long id;
    private String name;
    private String pictureUrl;
    private Boolean isFollow = false;

    public ProducerListResponseDto(Producer producer, Set<Long> producerFollowInfo) {
        this.id = producer.getId();
        this.name = producer.getName();
        this.pictureUrl = producer.getPicture().getUrl();
        this.isFollow = producerFollowInfo.contains(producer.getId());
    }

}
