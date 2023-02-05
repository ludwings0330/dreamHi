package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.VolunteerState;
import com.querydsl.core.annotations.QueryProjection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class VolunteerSearchResponseDto {

    private Long castingId;

    private String castingName;

    @Builder.Default
    private Map<VolunteerState, Long> stateSummary;

    @Builder.Default
    private List<VolunteerSimpleInfo> volunteers = new ArrayList<>();

    public VolunteerSearchResponseDto() {
    }

    @QueryProjection
    public VolunteerSearchResponseDto(Long castingId, String castingName, List<VolunteerSimpleInfo> volunteers) {
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

        private Double height;

        private Integer age;

        private Boolean isFollow;

        @QueryProjection
        public VolunteerSimpleInfo(Long userId, String imageUrl, String name, VolunteerState state, Double height, Integer age, Boolean isFollow) {
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
