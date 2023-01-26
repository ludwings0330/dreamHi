package com.elephant.dreamhi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private Long id;

    private String accessToken;

    private String refreshToken;

}
