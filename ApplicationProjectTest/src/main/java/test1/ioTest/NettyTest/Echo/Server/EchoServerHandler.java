package test1.ioTest.NettyTest.Echo.Server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import test1.ioTest.NettyTest.Echo.NettyChanelUtil;

public class EchoServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server通道激活："+ JSON.toJSONString(ctx));
        if(!NettyChanelUtil.serverCtxs.contains(ctx)){
            synchronized (NettyChanelUtil.class){
                if(!NettyChanelUtil.serverCtxs.contains(ctx)){
                    NettyChanelUtil.serverCtxs.add(ctx);
                }
            }
        }
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,String msg){
        System.out.println("Server received:"+msg);
        ctx.writeAndFlush("来自服务端ack:"+msg);
        ctx.fireChannelRead(msg);
    }

   /* @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }*/

    @Override
    public  void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
