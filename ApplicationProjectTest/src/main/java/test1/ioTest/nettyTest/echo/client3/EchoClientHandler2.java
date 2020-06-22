package test1.ioTest.nettyTest.echo.client3;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import test1.ioTest.nettyTest.echo.MyClientHandler;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

public class EchoClientHandler2 extends SimpleChannelInboundHandler<Object> {

    public static final String clientId="client2";

    public String getClientId(){
        return this.clientId;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
      System.out.println("");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush("auth_"+getClientId());
        ctx.fireChannelActive();
    }
}
