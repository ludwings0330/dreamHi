package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.FollowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequestDto {

    private FollowType type;

    private Long actorId;

    private Long producerId;

    private Long announcementId;

}
