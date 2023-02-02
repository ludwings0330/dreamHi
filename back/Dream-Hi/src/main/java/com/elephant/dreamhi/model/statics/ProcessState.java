package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProcessState {

    IN_PROGRESS,
    FINISH;

    @JsonCreator
    public static ProcessState from(String s) {
        return ProcessState.valueOf(s.toUpperCase());
    }

}
