package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
        if (this.isFollow == null) {
            return Boolean.FALSE;
        }

        return this.isFollow;
    }

    public Boolean getIsVolunteer() {
        if (this.isVolunteer == null) {
            return Boolean.FALSE;
        }

        return this.isVolunteer;
    }

}
