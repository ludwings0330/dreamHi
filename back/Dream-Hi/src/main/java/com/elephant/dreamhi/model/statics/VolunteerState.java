package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VolunteerState {

    NONE,
    PASS,
    FAIL,
    WAIT;

    @JsonCreator
    public static VolunteerState from(String s) {
        return VolunteerState.valueOf(s.toUpperCase());
    }

}
