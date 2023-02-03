package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStageName {

    NONE(0), // 모집 중(지원하지 않음)
    SUBMIT(1), // 지원 완료
    FAIL(2), // 불합격
    IN_PROGRESS(3), // 오디션 진행 중
    PASS(4); // 합격

    private final int priority;

    UserStageName(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    @JsonCreator
    public static UserStageName from(String s) {
        return UserStageName.valueOf(s.toUpperCase());
    }

}
