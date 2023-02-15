package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.Gender;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private Long producerId;

    private Boolean isFollow;

    private Boolean isVolunteer;

    public Boolean getIsFollow() {
        return Objects.requireNonNullElse(this.isFollow, Boolean.FALSE);
    }

    public Boolean getIsVolunteer() {
        return Objects.requireNonNullElse(this.isVolunteer, Boolean.FALSE);
    }

    public boolean validate() {
        if (this.minHeight != null && this.maxHeight != null) {
            return this.minHeight <= this.maxHeight;
        }

        if (this.minAge != null && this.maxAge != null) {
            return this.minAge <= this.maxAge;
        }

        return true;
    }

}
