package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Casting;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CastingSimpleDto {

    @NotNull
    private Integer headCount;

    @NotNull
    private String name;

    public static CastingSimpleDto toDto(Casting casting) {
        return new CastingSimpleDto(casting.getHeadcount(), casting.getName());
    }

}
