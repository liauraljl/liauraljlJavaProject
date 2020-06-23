package com.ljl.example.netty.sentinel.server;

import com.alibaba.csp.sentinel.cluster.server.codec.netty.NettyRequestDecoder;
import com.alibaba.csp.sentinel.cluster.server.codec.netty.NettyResponseEncoder;
import com.alibaba.csp.sentinel.cluster.server.connection.ConnectionPool;
import com.alibaba.csp.sentinel.cluster.server.init.DefaultClusterServerInitFunc;
import com.ljl.example.netty.socket.Server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jinlei.li
 * @date 2020/6/8 11:59
 * @description
 */
public class NettyServer {
    private static final ConnectionPool connectionPool = new ConnectionPool();


    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newFixedThreadPool(2);

        new DefaultClusterServerInitFunc().init();

        executorService.execute(()->{
            // 1 创建两个线程组
            // 一个是用于处理服务器端接收客户端连接的
            // 一个是进行网络通信的（网络读写的）
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                // 2 创建辅助工具类，用于服务器通道的一系列配置
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)// 指定NIO的模式
                        .option(ChannelOption.SO_BACKLOG, 1024)
                        .handler(new LoggingHandler(LogLevel.INFO))

                        .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline p = ch.pipeline();
                                        //编码通道处理
                                    p.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));

                                    p.addLast(new NettyRequestDecoder());
                                    p.addLast(new LengthFieldPrepender(2));

                                    //转码通道处理
                                    p.addLast(new NettyResponseEncoder());
                                    //聊天服务通道处理
                                    p.addLast(new ServerHandler(connectionPool));
                                        }
                                })
                        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                        .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                        .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                        .childOption(ChannelOption.SO_TIMEOUT, 10)
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        .childOption(ChannelOption.SO_RCVBUF, 32 * 1024);
                // 3、绑定端口 同步等待成功
                //ChannelFuture f = b.bind(11111).sync();
                // 4、等待服务端监听端口关闭
                //f.channel().closeFuture().sync();

                b.bind(11111).addListener(new GenericFutureListener<ChannelFuture>() {
                    @Override
                    public void operationComplete(ChannelFuture future) {
                        if (future.cause() != null) {
                            System.out.println("server cause !=null");
                            return;
                        } else {
                            System.out.println("server cause is null");
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //workerGroup.shutdownGracefully();
                //bossGroup.shutdownGracefully();
            }
        });

        /*executorService.execute(()->{
            while (true) {
                try {
                    //向客户端发送内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if(NettyChanelUtil.serverCtxs.size()>0){
                        Channel channel=NettyChanelUtil.serverCtxs.get(0).channel();
                        if(channel.isActive()){
                            channel.writeAndFlush(content);
                            System.out.println("isActive=true,服务端发出消息:"+content);
                        }
                        *//*if(channel.isOpen()){
                            channel.writeAndFlush(content);
                            System.out.println("isOpen服务端发出消息:"+content);
                        }*//*
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    private void start(){

    }

}
