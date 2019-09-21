package com.ljl.example.websocket.framework.service.strategy;

import com.ljl.example.websocket.framework.enums.ConstantEnum;
import com.ljl.example.websocket.framework.service.strategy.impl.SendLiveRoomCompRoomMsg;
import com.ljl.example.websocket.framework.service.strategy.impl.SendLiveRoomMsg;
import com.ljl.example.websocket.framework.service.strategy.impl.SendRoomMsg;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class StrategyContext {
    private SendMessage sendMessage;

    public void setSendMessage(ConstantEnum constantEnum){
        switch (constantEnum){
            case FRESH: this.sendMessage = new SendLiveRoomCompRoomMsg(); break;
            case BARGAINACTIV: this.sendMessage = new SendLiveRoomMsg(); break;
            case ROOMBARGAIN:
            case ROOMACTIVITYSTAT:
            case ORDER: this.sendMessage = new SendRoomMsg(); break;
        }
    }

    public Integer sendMsg(String content){
        return sendMessage.sendMsg(content);
    }

}
