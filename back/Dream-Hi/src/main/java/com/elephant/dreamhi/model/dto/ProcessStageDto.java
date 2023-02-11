package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Process;
import com.elephant.dreamhi.model.entity.Volunteer;
import com.elephant.dreamhi.model.statics.ProcessState;
import com.elephant.dreamhi.model.statics.StageName;
import com.elephant.dreamhi.model.statics.UserStageName;
import java.util.Comparator;
import java.util.List;
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
public class ProcessStageDto {

    @NotNull
    private Long id;

    @NotNull
    private ProcessState processState;

    @NotNull
    private StageName stageName;

    @NotNull
    private UserStageName userStageName;

    public ProcessStageDto(Process lastProcess, UserStageName userStageName) {
        this.id = lastProcess.getId();
        this.processState = lastProcess.getState();
        this.stageName = lastProcess.getStage();
        this.userStageName = userStageName;
    }

    public static ProcessStageDto toDto(Process lastProcess, List<Volunteer> volunteers) {
        ProcessState lastProcessState = lastProcess.getState();
        ProcessStageDtoBuilder dtoBuilder = ProcessStageDto.builder()
                                                           .id(lastProcess.getId())
                                                           .processState(lastProcessState)
                                                           .stageName(lastProcess.getStage());

        // 지원을 안한 경우
        if (volunteers.isEmpty()) {
            return dtoBuilder.userStageName(UserStageName.NONE).build();
        }

        // 지원을 했고, 공고의 현재 진행 상태가 모집 중인 경우
        if (lastProcessState == ProcessState.RECRUITING) {
            return dtoBuilder.userStageName(UserStageName.SUBMIT).build();
        }

        // 지원을 했고, 공고의 현재 진행 상태가 오디션 진행 중 또는 캐스팅 완료인 경우
        UserStageName resultStageName = volunteers.stream()
                                                  .map(volunteer -> {
                                                      if (!lastProcess.getId().equals(volunteer.getProcess().getId())) {
                                                          return UserStageName.FAIL;
                                                      }

                                                      if (lastProcessState == ProcessState.FINISH) {
                                                          return UserStageName.PASS;
                                                      }

                                                      return UserStageName.IN_PROGRESS;
                                                  })
                                                  .max(Comparator.comparingInt(UserStageName::getPriority))
                                                  .get();

        return dtoBuilder.userStageName(resultStageName).build();
    }

}
