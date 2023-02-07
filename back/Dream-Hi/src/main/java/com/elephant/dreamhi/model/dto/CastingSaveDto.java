package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class CastingSaveDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer headcount;

    private Integer minHeight;

    private Integer maxHeight;

    private Integer minAge;

    private Integer maxAge;

    private Gender gender;

    @Size.List({
            @Size(max = 5)
    })
    private List<Long> styles;

}
