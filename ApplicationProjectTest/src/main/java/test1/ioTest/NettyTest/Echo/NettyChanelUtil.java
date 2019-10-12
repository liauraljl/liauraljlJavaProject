package test1.ioTest.NettyTest.Echo;

import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/9/22.
 */
public class NettyChanelUtil {
    public static List<ChannelHandlerContext> clientCtxs=new ArrayList<>();
    public static List<ChannelHandlerContext> serverCtxs=new ArrayList<>();
}
