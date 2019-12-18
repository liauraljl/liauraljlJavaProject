package test1.ioTest.nettyTest.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import test1.ioTest.nettyTest.echo.NettyChanelUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoServer {
    public static void main(String[] args) throws Exception{
        new Thread(()->{
            // 1 创建两个线程组
            // 一个是用于处理服务器端接收客户端连接的
            // 一个是进行网络通信的（网络读写的）
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup(0);
            try {
                // 2 创建辅助工具类，用于服务器通道的一系列配置
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup);
                b.channel(NioServerSocketChannel.class);// 指定NIO的模式
                b.childOption(ChannelOption.SO_BACKLOG, 1024); // 设置tcp缓冲区
                b.childOption(ChannelOption.SO_SNDBUF, 32 * 1024); // 设置发送缓冲大小
                b.childOption(ChannelOption.SO_RCVBUF, 32 * 1024); // 这是接收缓冲大小
                b.childOption(ChannelOption.SO_KEEPALIVE, true); // 保持连接
                b.childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //编码通道处理
                        ch.pipeline().addLast("decode", new StringDecoder());
                        //转码通道处理
                        ch.pipeline().addLast("encode", new StringEncoder());
                        //聊天服务通道处理
                        ch.pipeline().addLast("chat", new EchoServerHandler());
                    }
                });
                // 3、绑定端口 同步等待成功
                ChannelFuture f = b.bind(11087).sync();
                // 4、等待服务端监听端口关闭
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //优雅关闭
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }).start();

        new Thread(()->{
            while (true) {
                try {
                    //向客户端发送内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if(content.contains("_")){
                        String clientId=content.split("_")[0];
                        if(NettyChanelUtil.serverCtxs.containsKey(clientId)){
                            Channel channel=NettyChanelUtil.serverCtxs.get(clientId).channel();
                            if(channel.isActive()){
                                channel.writeAndFlush(content.split("_")[1]);
                            }
                        }
                    }else{
                        if(NettyChanelUtil.serverCtxs.size()>0){
                            for(ChannelHandlerContext ctx:NettyChanelUtil.serverCtxs.values()){
                                Channel channel=ctx.channel();
                                if(channel.isActive()){
                                    channel.writeAndFlush(content);
                                    System.out.println("服务端发出消息:"+content);
                                }
                        /*if(channel.isOpen()){
                            channel.writeAndFlush(content);
                            System.out.println("isOpen服务端发出消息:"+content);
                        }*/
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
