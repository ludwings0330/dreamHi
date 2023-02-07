package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.StageName;
import javax.validation.constraints.NotNull;
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
public class ProcessSaveDto {

    @NotNull
    private Long announcementId;

    @NotNull
    private Long producerId;

    @NotNull
    private ProcessState state;

    private StageName stage;

}
