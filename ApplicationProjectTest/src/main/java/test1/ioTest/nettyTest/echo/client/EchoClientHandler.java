package test1.ioTest.nettyTest.echo.client;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client通道激活："+ JSON.toJSONString(ctx));
        ctx.writeAndFlush("通知服务端：客户端收到连接！");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if(!NettyChanelUtil.clientCtxs.contains(ctx)){
            synchronized (NettyChanelUtil.class){
                if(!NettyChanelUtil.clientCtxs.contains(ctx)){
                    NettyChanelUtil.clientCtxs.add(ctx);
                }
            }
        }
        System.out.println("client received:"+msg);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
