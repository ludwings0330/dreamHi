package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FollowType {

    ACTOR,
    PRODUCER,
    ANNOUNCEMENT;

    @JsonCreator
    public static FollowType from(String s) {
        return FollowType.valueOf(s.toUpperCase());
    }

}
