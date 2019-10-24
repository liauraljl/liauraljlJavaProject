package test1.ioTest.nettyTest.echo.client2;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

public class EchoClientHandler2 extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client2通道激活："+ JSON.toJSONString(ctx));
        ctx.writeAndFlush("auth_client2");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if(msg.contains("auth")) {
            String clientId = msg.split("_")[1];
            if (!NettyChanelUtil.clientCtxs.containsKey(clientId)) {
                synchronized (NettyChanelUtil.clientCtxs) {
                    if (!NettyChanelUtil.clientCtxs.containsKey(clientId)) {
                        NettyChanelUtil.clientCtxs.put(clientId, ctx);
                        ctx.writeAndFlush(msg);
                    }
                }
            }
        }else{
            System.out.println("client2 received:"+msg);
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
