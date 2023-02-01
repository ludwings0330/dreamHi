package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Style;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StyleDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 15)
    private String description;

    public static StyleDto toDto(Style style) {
        return new StyleDto(style.getId(), style.getDescription());
    }

}
