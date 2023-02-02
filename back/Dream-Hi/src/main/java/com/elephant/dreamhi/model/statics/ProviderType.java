package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProviderType {
    GOOGLE,
    NAVER,
    KAKAO;

    @JsonCreator
    public static ProviderType from(String s) {
        return ProviderType.valueOf(s.toUpperCase());
    }

}
