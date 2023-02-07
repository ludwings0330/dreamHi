package com.elephant.dreamhi.model.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class CastingUpdateDto extends CastingSaveDto {

    @NotNull
    private Long id;

}
