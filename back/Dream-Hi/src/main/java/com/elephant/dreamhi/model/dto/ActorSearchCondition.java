package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class ActorSearchCondition {

    @Nullable
    private Long id;

    @Nullable
    private String name;

    @Nullable
    private Integer height;

    @Nullable
    private Integer age;

    @Nullable
    private Gender gender;

    @Nullable
    private List<Long> styles = new ArrayList<>();

    @Nullable
    private Boolean isFollow = false;

}
