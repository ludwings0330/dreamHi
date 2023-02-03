package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProducerRole {

    EDITOR,
    MEMBER;

    @JsonCreator
    public static ProducerRole from(String s) {
        return ProducerRole.valueOf(s.toUpperCase());
    }

}
