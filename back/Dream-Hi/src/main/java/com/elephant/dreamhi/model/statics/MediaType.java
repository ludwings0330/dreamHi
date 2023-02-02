package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MediaType {
    PICTURE,
    VIDEO;

    @JsonCreator
    public static MediaType from(String s) {
        return MediaType.valueOf(s.toUpperCase());
    }

}
