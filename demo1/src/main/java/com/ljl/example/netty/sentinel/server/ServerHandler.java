package com.ljl.example.netty.sentinel.server;

import com.alibaba.csp.sentinel.cluster.ClusterConstants;
import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;
import com.alibaba.csp.sentinel.cluster.response.ClusterResponse;
import com.alibaba.csp.sentinel.cluster.response.data.FlowTokenResponseData;
import com.alibaba.csp.sentinel.cluster.server.connection.ConnectionManager;
import com.alibaba.csp.sentinel.cluster.server.connection.ConnectionPool;
import com.alibaba.csp.sentinel.cluster.server.processor.RequestProcessor;
import com.alibaba.csp.sentinel.cluster.server.processor.RequestProcessorProvider;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.fastjson.JSON;
import com.ljl.example.netty.socket.NettyChanelUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

/**
 * @author jinlei.li
 * @date 2020/6/8 11:59
 * @description
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private final ConnectionPool globalConnectionPool;

    public ServerHandler(ConnectionPool globalConnectionPool) {
        this.globalConnectionPool = globalConnectionPool;
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server通道激活："+ JSON.toJSONString(ctx));
        globalConnectionPool.createConnection(ctx.channel());
        String remoteAddress = getRemoteAddress(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String remoteAddress = getRemoteAddress(ctx);
        globalConnectionPool.remove(ctx.channel());
        ConnectionManager.removeConnection(remoteAddress);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        //System.out.println("server received:"+msg);
        globalConnectionPool.refreshLastReadTime(ctx.channel());

        if (msg instanceof ClusterRequest) {
            ClusterRequest request = (ClusterRequest)msg;

            // Client ping with its namespace, add to connection manager.
            if (request.getType() == ClusterConstants.MSG_TYPE_PING) {
                //handlePingRequest(ctx, request);
                return;
            }

            FlowTokenResponseData data=new FlowTokenResponseData().setRemainingCount(19).setWaitInMs(10);
            ClusterResponse<FlowTokenResponseData> response = new ClusterResponse<>();
            response.setId(request.getId());
            response.setType(1);
            response.setStatus(1);
            response.setData(data);
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public  void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

    private String getRemoteAddress(ChannelHandlerContext ctx) {
        if (ctx.channel().remoteAddress() == null) {
            return null;
        }
        InetSocketAddress inetAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        return inetAddress.getAddress().getHostAddress() + ":" + inetAddress.getPort();
    }
}
