package com.elephant.dreamhi.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class VolunteerApplyRequestDto {

    private List<Long> castingIds;
    private Long announcementId;
    private Long userId;

}
