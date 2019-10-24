package test1.ioTest.nettyTest.echo;

import io.netty.channel.ChannelHandlerContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liaura_ljl on 2019/9/22.
 */
public class NettyChanelUtil {
    public static Map<String,ChannelHandlerContext> clientCtxs=new ConcurrentHashMap<>();
    public static Map<String,ChannelHandlerContext> serverCtxs=new ConcurrentHashMap<>();
}
