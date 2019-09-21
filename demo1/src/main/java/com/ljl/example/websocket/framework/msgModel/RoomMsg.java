package com.ljl.example.websocket.framework.msgModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomMsg {
    private String liveCode;
    private Object msg;
}
