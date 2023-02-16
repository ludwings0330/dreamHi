package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.VolunteerState;
import com.querydsl.core.annotations.QueryProjection;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class VolunteerSearchResponseDto {

    private Long castingId;

    private String castingName;

    private Integer headCount;

    @Builder.Default
    private Map<VolunteerState, Long> stateSummary;

    @Builder.Default
    private Page<VolunteerSimpleInfo> volunteers;

    public VolunteerSearchResponseDto() {
    }

    @QueryProjection
    public VolunteerSearchResponseDto(Long castingId, String castingName, Page<VolunteerSimpleInfo> volunteers) {
        this.castingId = castingId;
        this.castingName = castingName;
        this.volunteers = volunteers;
    }

    @Data
    public static class VolunteerSimpleInfo {

        private Long userId;

        private String imageUrl;

        private String name;

        private VolunteerState state;

        private Integer height;

        private Integer age;

        private Boolean isFollow;

        @QueryProjection
        public VolunteerSimpleInfo(Long userId, String imageUrl, String name, VolunteerState state, Integer height, Integer age, Boolean isFollow) {
            this.userId = userId;
            this.imageUrl = imageUrl;
            this.name = name;
            this.state = state;
            this.height = height;
            this.age = age;
            this.isFollow = isFollow;
        }

    }

}
