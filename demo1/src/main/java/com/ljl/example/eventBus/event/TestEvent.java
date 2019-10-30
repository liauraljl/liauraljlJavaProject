package com.ljl.example.eventBus.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestEvent {

    private Integer code;

    private String content;
}
