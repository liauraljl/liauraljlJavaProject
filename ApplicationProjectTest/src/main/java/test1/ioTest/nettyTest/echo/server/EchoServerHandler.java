package test1.ioTest.nettyTest.echo.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

public class EchoServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server通道激活："+ JSON.toJSONString(ctx));
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx,String msg){
        if(msg.contains("auth")){
            String clientId=msg.split("_")[1];
            if(!NettyChanelUtil.serverCtxs.containsKey(clientId)){
                synchronized (NettyChanelUtil.serverCtxs){
                    if(!NettyChanelUtil.serverCtxs.containsKey(clientId)){
                        NettyChanelUtil.serverCtxs.put(clientId,ctx);
                        ctx.writeAndFlush(msg);
                    }
                }
            }
        }else{
            System.out.println("server received:"+msg);
            //ctx.writeAndFlush("来自服务端ack:"+msg);
            ctx.fireChannelRead(msg);
        }
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
