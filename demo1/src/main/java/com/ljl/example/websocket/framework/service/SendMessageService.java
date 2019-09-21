package com.ljl.example.websocket.framework.service;

import com.alibaba.fastjson.JSON;
import com.ljl.example.websocket.framework.enums.ConstantEnum;
import com.ljl.example.websocket.framework.service.strategy.StrategyContext;
import com.ljl.example.websocket.framework.util.NettyConnectionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    //发送砍价消息和下单消息到场次  发送下单消息和模板消息给直播间
    public Integer sendMsgToLiveRoom(ConstantEnum constantEnum, String content){
        log.info("WebSocket发送数据：{}", JSON.toJSONString(content));
        StrategyContext context = new StrategyContext();
        context.setSendMessage(constantEnum);
        return context.sendMsg(content);
    }

    /**
     * 发送消息
     * @param user
     * @param msg
     */
    public void sendMsg(Long user,String msg){
        if(NettyConnectionUtil.userChannelMap.containsKey(user)){
            ChannelHandlerContext ctx=NettyConnectionUtil.userChannelMap.get(user);
            ctx.writeAndFlush(new TextWebSocketFrame(msg));
        }

    }


}
