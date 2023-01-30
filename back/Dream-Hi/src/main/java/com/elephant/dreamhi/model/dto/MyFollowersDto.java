package com.elephant.dreamhi.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyFollowersDto {

    private Long id;

    private LocalDateTime date;

    private Long followerId;

    private String email;

    private String name;

    private String pictureId;

}
