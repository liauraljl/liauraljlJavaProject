package com.ljl.example.netty.websocket.service.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.ljl.example.netty.websocket.enums.ConstantEnum;
import com.ljl.example.netty.websocket.msgModel.WebSocketMsgModel;
import com.ljl.example.netty.websocket.request.IMRoomRequest;
import com.ljl.example.netty.websocket.service.strategy.SendMessage;
import com.ljl.example.netty.websocket.util.NettyConnectionUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 发送直播间消息,提醒消息
 * 消息格式 MsgType_json
 */
@Slf4j
public class SendLiveRoomMsg implements SendMessage {

    @Override
    public Integer sendMsg(String msg) {
        Integer result = 0;
        try{
            WebSocketMsgModel webSocketMsgModel = JSON.parseObject(msg, WebSocketMsgModel.class);
            IMRoomRequest imRoomRequest = JSON.parseObject(JSON.toJSONString(webSocketMsgModel.getData()), IMRoomRequest.class);
            List<String> wids = imRoomRequest.getWids();
            log.info("发送直播间数据：{},现存的通道数：{}", JSON.toJSON(wids), JSON.toJSON(NettyConnectionUtil.userChannelMap.entrySet()));
            int size = NettyConnectionUtil.userChannelMap.size();
            if(wids!=null && size>0){
                result = wids.size();
                wids.stream().forEach(t->{
                    Long wid = Long.parseLong(t);
                    log.info("身份wid:{}",wid);
                    if(NettyConnectionUtil.userChannelMap.containsKey(wid)){
                        WebSocketMsgModel<Object> response = new WebSocketMsgModel(webSocketMsgModel.getMsgType(), imRoomRequest.getContent());
                        String jsonData = JSON.toJSONString(response);
                        log.info("消息类型：{}，消息内容：{}", ConstantEnum.getByInt(webSocketMsgModel.getMsgType()),jsonData);
                        log.info("通过通道：{}，wid:{}，发送：{}",NettyConnectionUtil.userChannelMap.get(wid),wid,jsonData);
                        NettyConnectionUtil.userChannelMap.get(wid).writeAndFlush(new TextWebSocketFrame(jsonData.replaceAll("\"","\'")));
                    }
                });
            }
        }catch (Exception e){
            log.error("发送直播间场次消息异常",e);
        }
        return result;
    }
}
