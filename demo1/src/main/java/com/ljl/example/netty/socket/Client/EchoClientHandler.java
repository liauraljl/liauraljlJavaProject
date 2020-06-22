package com.ljl.example.netty.socket.Client;

import com.alibaba.csp.sentinel.cluster.ClusterConstants;
import com.alibaba.csp.sentinel.cluster.client.handler.TokenClientPromiseHolder;
import com.alibaba.csp.sentinel.cluster.registry.ConfigSupplierRegistry;
import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;
import com.alibaba.csp.sentinel.cluster.response.ClusterResponse;
import com.alibaba.fastjson.JSON;
import com.ljl.example.netty.socket.NettyChanelUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client通道激活："+ JSON.toJSONString(ctx));
        fireClientPing(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client read :"+msg);
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
