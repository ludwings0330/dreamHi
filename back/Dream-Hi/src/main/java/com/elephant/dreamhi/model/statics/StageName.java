package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StageName {

    DOC,
    VIDEO,
    OFF;

    @JsonCreator
    public static StageName from(String s) {
        return StageName.valueOf(s.toUpperCase());
    }

}
