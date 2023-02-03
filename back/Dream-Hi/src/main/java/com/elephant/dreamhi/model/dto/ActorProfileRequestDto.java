package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorProfileRequestDto {

    private Long actorProfileId;
    private String name;
    private String title;
    private Gender gender;
    private Integer age;
    private Double height;
    private String description;
    private List<Long> deleteStyles = new ArrayList<>();
    private List<Long> insertStyles = new ArrayList<>();

}
