package test1.ioTest.nettyTest.echo;

import io.netty.channel.ChannelHandlerContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChanelUtil {
    public static Map<String,ChannelHandlerContext> clientCtxs=new ConcurrentHashMap<>();
    public static Map<String,ChannelHandlerContext> serverCtxs=new ConcurrentHashMap<>();
}
