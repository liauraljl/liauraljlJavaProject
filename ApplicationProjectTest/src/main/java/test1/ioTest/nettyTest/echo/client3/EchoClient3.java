package test1.ioTest.nettyTest.echo.client3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class EchoClient3 {
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
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                        //pipeline.addLast(new NettyResponseDecoder());
                        pipeline.addLast(new LengthFieldPrepender(2));
                        //pipeline.addLast(new NettyRequestEncoder());
                        //pipeline.addLast(clientHandler);
                    }
                });
                ChannelFuture f = b.connect("127.0.0.1",11111).sync();
                f.channel().closeFuture().sync();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
        }).start();

        /*new Thread(()->{
            while (true) {
                try {
                    //向服务端发送内容
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if (NettyChanelUtil.clientCtxs.containsKey(EchoClientHandler2.clientId)) {
                        Channel channel = NettyChanelUtil.clientCtxs.get(EchoClientHandler2.clientId).channel();
                        if (channel.isActive()) {
                            channel.writeAndFlush(content);
                            System.out.println("客户端2发出消息:" + content);
                        }
                        *//*if (channel.isOpen()) {
                            channel.writeAndFlush(content);
                            System.out.println("isOpen客户端发出消息:" + content);
                        }*//*
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
}
