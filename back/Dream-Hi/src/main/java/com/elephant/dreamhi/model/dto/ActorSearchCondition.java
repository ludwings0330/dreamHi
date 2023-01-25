package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class ActorSearchCondition {

    @Nullable
    private String name;

    @Nullable
    private Integer height;

    @Nullable
    private Integer age;

    @Nullable
    private Gender gender;

    @Nullable
    private String[] styles;

    @Nullable
    private Boolean isFollow = false;

}
