package test1.ioTest.nettyTest.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoClient {
    public static void main(String[] args) throws Exception{
        new Thread(()->{
            EventLoopGroup group=new NioEventLoopGroup();
            try{
                Bootstrap b=new Bootstrap();
                b.group(group)
                .channel(NioSocketChannel.class)
                //.remoteAddress(new InetSocketAddress("localhost",11087))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception{
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast("chat",new EchoClientHandler());
                    }
                });
                ChannelFuture f = b.connect("localhost",11087).sync();
                f.channel().closeFuture().sync();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
        }).start();

        new Thread(()->{
            while (true) {
                try {
                    //向服务端发送内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if (NettyChanelUtil.clientCtxs.containsKey(EchoClientHandler.clientId)) {
                        Channel channel = NettyChanelUtil.clientCtxs.get(EchoClientHandler.clientId).channel();
                        if (channel.isActive()) {
                            channel.writeAndFlush(content);
                            System.out.println("客户端1发出消息:" + content);
                        }
                        /*if (channel.isOpen()) {
                            channel.writeAndFlush(content);
                            System.out.println("isOpen客户端发出消息:" + content);
                        }*/
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
