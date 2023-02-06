package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.VolunteerState;
import lombok.Data;

@Data
public class VolunteerManageRequestDto {

    private Long announcementId;
    private Long volunteerId;
    private Long userId;
    private VolunteerState state;

}
