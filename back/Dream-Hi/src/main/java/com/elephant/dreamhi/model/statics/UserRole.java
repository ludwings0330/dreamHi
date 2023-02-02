package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {

    ROLE_USER,
    ROLE_ANONYMOUS;

    @JsonCreator
    public static UserRole from(String s) {
        return UserRole.valueOf(s.toUpperCase());
    }

}
