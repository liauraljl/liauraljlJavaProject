package test1.ioTest.nettyTest.echo;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println(getClientId()+"通道激活："+ JSON.toJSONString(ctx));
        if (!NettyChanelUtil.clientCtxs.containsKey(getClientId())) {
            synchronized (NettyChanelUtil.clientCtxs) {
                if (!NettyChanelUtil.clientCtxs.containsKey(getClientId())) {
                    NettyChanelUtil.clientCtxs.put(getClientId(), ctx);
                }
            }
        }
        ctx.writeAndFlush("auth_"+getClientId());
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(getClientId()+" received:"+msg);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

    public abstract String getClientId();
}
