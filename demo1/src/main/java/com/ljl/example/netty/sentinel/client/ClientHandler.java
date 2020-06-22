package com.ljl.example.netty.sentinel.client;
import com.alibaba.csp.sentinel.cluster.ClusterConstants;
import com.alibaba.csp.sentinel.cluster.response.ClusterResponse;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jinlei.li
 * @date 2020/6/8 11:59
 * @description
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final AtomicInteger currentState;
    private final Runnable disconnectCallback;

    public ClientHandler(AtomicInteger currentState, Runnable disconnectCallback) {
        this.currentState = currentState;
        this.disconnectCallback = disconnectCallback;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client通道激活："+ JSON.toJSONString( ctx));
        ctx.writeAndFlush("通知服务端：客户端收到连接！");
        //ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("client received:"+msg);
        if(msg instanceof ClusterResponse){
            ClusterResponse<?> response = (ClusterResponse) msg;

            if (response.getType() == ClusterConstants.MSG_TYPE_PING) {
                //handlePingResponse(ctx, response);
                return;
            }

            TokenClientPromiseHolder.completePromise(response.getId(), response);
        }

        //ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
