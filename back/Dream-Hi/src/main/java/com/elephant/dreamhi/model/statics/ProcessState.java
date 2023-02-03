package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProcessState {

    RECRUITING, // 모집 중
    IN_PROGRESS, // 오디션 진행 중
    FINISH; // 모집 완료

    @JsonCreator
    public static ProcessState from(String s) {
        return ProcessState.valueOf(s.toUpperCase());
    }

}
