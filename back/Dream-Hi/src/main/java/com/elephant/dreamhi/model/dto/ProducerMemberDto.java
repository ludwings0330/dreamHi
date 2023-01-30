package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.UserProducerRelation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerMemberDto {

    private String name;
    private String position;
    private String pictureUrl;

    public ProducerMemberDto(UserProducerRelation member) {
        this.name = member.getUser().getName();
        this.position = member.getPosition();
        this.pictureUrl = member.getUser().getPicture().getUrl();
    }

}
