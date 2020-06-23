package com.ljl.example.netty.sentinel.client;
import com.alibaba.csp.sentinel.cluster.ClusterConstants;
import com.alibaba.csp.sentinel.cluster.registry.ConfigSupplierRegistry;
import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;
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
        fireClientPing(ctx);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //System.out.println("client received:"+msg);
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

    private void fireClientPing(ChannelHandlerContext ctx) {
        // Data body: namespace of the client.
        ClusterRequest<String> ping = new ClusterRequest<String>().setId(0)
                .setType(ClusterConstants.MSG_TYPE_PING)
                .setData(ConfigSupplierRegistry.getNamespaceSupplier().get());
        ctx.writeAndFlush(ping);
    }
}
