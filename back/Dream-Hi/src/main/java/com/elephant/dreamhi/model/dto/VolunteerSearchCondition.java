package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.VolunteerState;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class VolunteerSearchCondition {

    private Long announcementId;

    private Long userId;

    private Long processId;

    private Long castingId;

    private VolunteerState state;

    private Integer minAge;

    private Integer maxAge;

    private Integer minHeight;

    private Integer maxHeight;

    private String name;

    private Pageable pageable;

}
