package com.elephant.dreamhi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private String owner;
    private String sender;
    private String time;
    private String content;
    private String read;

}
