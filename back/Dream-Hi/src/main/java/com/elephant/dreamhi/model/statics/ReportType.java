package com.elephant.dreamhi.model.statics;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportType {

    USER,
    PRODUCER;

    @JsonCreator
    public static ReportType from(String s) {
        return ReportType.valueOf(s.toUpperCase());
    }

}
