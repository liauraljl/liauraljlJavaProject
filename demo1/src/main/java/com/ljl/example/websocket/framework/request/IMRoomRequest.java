package com.ljl.example.websocket.framework.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IMRoomRequest implements Serializable {
    private List<String> wids;
    private String messageType;
    private Object content;
}
