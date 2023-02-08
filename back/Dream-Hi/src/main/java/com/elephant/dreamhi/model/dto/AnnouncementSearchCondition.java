package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class AnnouncementSearchCondition {

    private Integer minHeight;

    private Integer maxHeight;

    private Integer minAge;

    private Integer maxAge;

    private Gender gender;

    private String keyword;

    private Long[] styles;

    private Boolean isFollow = Boolean.FALSE;

    private Boolean isVolunteer = Boolean.FALSE;

    public Boolean getIsFollow() {
        return Objects.requireNonNullElse(this.isFollow, Boolean.FALSE);
    }

    public Boolean getIsVolunteer() {
        return Objects.requireNonNullElse(this.isVolunteer, Boolean.FALSE);
    }

}
