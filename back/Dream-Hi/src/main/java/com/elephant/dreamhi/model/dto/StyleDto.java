package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Style;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StyleDto {

    @Nullable
    private Long id;

    @Nullable
    @Size(max = 15)
    private String description;

    public static StyleDto toDto(Style style) {
        return new StyleDto(style.getId(), style.getDescription());
    }

}
