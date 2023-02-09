package com.elephant.dreamhi.model.dto;

import lombok.Data;

@Data
public class ProducerSearchCondition {

    private String name;
    private Long userId;
    private Boolean isFollow;
    private Boolean involve;

}
