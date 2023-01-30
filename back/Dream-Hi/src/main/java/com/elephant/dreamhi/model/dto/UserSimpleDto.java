package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSimpleDto {

    private Long id;

    private String name;

    private String pictureUrl;

    public void setUserSimpleDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.pictureUrl = user.getPicture() == null ? null : user.getPicture().getUrl();
    }

}
