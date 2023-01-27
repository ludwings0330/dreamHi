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
public class UserDetailDto {

    private Long id;

    private String email;

    private String name;

    private String phone;

    private String pictureUrl;

    public void setUserDetailDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.pictureUrl = user.getPicture().getUrl();
    }

}
