package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Casting;
import com.elephant.dreamhi.model.entity.CastingStyleRelation;
import com.elephant.dreamhi.model.entity.Style;
import com.elephant.dreamhi.model.statics.Gender;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CastingDetailDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    private Integer headcount;

    private Integer minHeight;

    private Integer maxHeight;

    private Integer minAge;

    private Integer maxAge;

    private Gender gender;

    private List<StyleDto> styles;

    public CastingDetailDto(Casting casting) {
        this.styles = casting.getCastingStyleRelations()
                             .stream()
                             .map(CastingStyleRelation::getStyle)
                             .map(StyleDto::toDto)
                             .collect(Collectors.toList());

        this.id = casting.getId();
        this.name = casting.getName();
        this.description = casting.getDescription();
        this.headcount = casting.getHeadcount();
        this.minHeight = casting.getMinHeight();
        this.maxHeight = casting.getMaxHeight();
        this.minAge = casting.getMinAge();
        this.maxAge = casting.getMaxAge();
        this.gender = casting.getGender();
    }

    public static CastingDetailDto toDto(Casting casting, List<Style> styles) {
        return CastingDetailDto.builder()
                               .id(casting.getId())
                               .name(casting.getName())
                               .description(casting.getDescription())
                               .headcount(casting.getHeadcount())
                               .minHeight(casting.getMinHeight())
                               .maxHeight(casting.getMaxHeight())
                               .minAge(casting.getMinAge())
                               .maxAge(casting.getMaxAge())
                               .gender(casting.getGender())
                               .styles(styles.stream()
                                             .map(StyleDto::toDto)
                                             .collect(Collectors.toList()))
                               .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CastingDetailDto that = (CastingDetailDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(
                getDescription(), that.getDescription()) && Objects.equals(getHeadcount(), that.getHeadcount()) && Objects.equals(
                getMinHeight(), that.getMinHeight()) && Objects.equals(getMaxHeight(), that.getMaxHeight()) && Objects.equals(
                getMinAge(), that.getMinAge()) && Objects.equals(getMaxAge(), that.getMaxAge()) && getGender() == that.getGender()
                && Objects.equals(getStyles(), that.getStyles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getHeadcount(), getMinHeight(), getMaxHeight(), getMinAge(), getMaxAge(),
                            getGender(),
                            getStyles());
    }

}
