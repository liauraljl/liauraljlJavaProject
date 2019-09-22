package com.ljl.example.netty.websocket.service.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.ljl.example.netty.websocket.enums.ConstantEnum;
import com.ljl.example.netty.websocket.msgModel.RoomMsg;
import com.ljl.example.netty.websocket.msgModel.WebSocketMsgModel;
import com.ljl.example.netty.websocket.service.strategy.SendMessage;
import com.ljl.example.netty.websocket.util.NettyConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * 发送直播间消息
 * 消息格式   liveCode_价钱  liveCode_下单消息
 */
@Slf4j
public class SendRoomMsg implements SendMessage {
    @Override
    public Integer sendMsg(String msg) {
        //消息格式 liveCode_价钱
        Integer result = 0;
        try{
            WebSocketMsgModel webSocketMsgModel = JSON.parseObject(msg, WebSocketMsgModel.class);
            RoomMsg roomMsg = JSON.parseObject(JSON.toJSONString(webSocketMsgModel.getData()), RoomMsg.class);
            Assert.notNull(roomMsg,"直播间场次信息不为空！");
            Assert.notNull(roomMsg.getMsg(),"场次信息不为空！");
            String liveCode = roomMsg.getLiveCode();
            List<ChannelHandlerContext> channelHandlerContexts = NettyConnectionUtil.roomChannelId.get(liveCode);
            if(channelHandlerContexts!=null&&channelHandlerContexts.size()>0){
                WebSocketMsgModel<Object> response = new WebSocketMsgModel<Object>(webSocketMsgModel.getMsgType(), roomMsg.getMsg());
                String jsonData = JSON.toJSONString(response);
                log.info("消息类型：{}，消息内容：{}", ConstantEnum.getByInt(webSocketMsgModel.getMsgType()),jsonData);
                log.info("通道:{},消息：{}", Arrays.toString(channelHandlerContexts.toArray()),jsonData);
                channelHandlerContexts.parallelStream().forEach(t->t.writeAndFlush(new TextWebSocketFrame(jsonData.replaceAll("\"","\'"))));
                result = channelHandlerContexts.size();
            }

        }catch (Exception e){
            log.error("发送直播间场次消息异常",e);
        }
        return result;
    }
}
