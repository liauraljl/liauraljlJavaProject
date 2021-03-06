package com.ljl.example.netty.sentinel.client;

import com.alibaba.csp.sentinel.cluster.ClusterConstants;
import com.alibaba.csp.sentinel.cluster.client.ClientConstants;
import com.alibaba.csp.sentinel.cluster.client.codec.netty.NettyRequestEncoder;
import com.alibaba.csp.sentinel.cluster.client.codec.netty.NettyResponseDecoder;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.cluster.client.init.DefaultClusterClientInitFunc;
import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;
import com.alibaba.csp.sentinel.cluster.request.data.FlowRequestData;
import com.alibaba.csp.sentinel.cluster.response.ClusterResponse;
import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jinlei.li
 * @date 2020/6/8 11:59
 * @description
 */
public class NettyClient {

    private Channel channel;

    private final AtomicInteger idGenerator = new AtomicInteger(0);

    private NioEventLoopGroup eventLoopGroup;

    private final AtomicInteger currentState = new AtomicInteger(ClientConstants.CLIENT_STATUS_OFF);

    private final AtomicBoolean shouldRetry = new AtomicBoolean(true);

    private ConcurrentHashMap<Integer, Long> startTimes = new ConcurrentHashMap<>();

    private AtomicLong successCounter = new AtomicLong(0);
    private AtomicLong requestCounter = new AtomicLong(0);


    public static final int RECONNECT_DELAY_MS = 2000;

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1,
            new NamedThreadFactory("sentinel-cluster-transport-client-scheduler"));


    private static final ExecutorService executorService = Executors.newFixedThreadPool(32);


    public static void main(String[] args) throws Exception {

        new NettyClient().start();
    }

    private void start() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Bootstrap b = new Bootstrap();
        executorService.execute(() -> {
            eventLoopGroup = new NioEventLoopGroup();
            try {
                b.group(eventLoopGroup)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, ClusterClientConfigManager.getConnectTimeout())
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));
                                ch.pipeline().addLast(new NettyResponseDecoder());
                                ch.pipeline().addLast(new LengthFieldPrepender(2));
                                ch.pipeline().addLast(new NettyRequestEncoder());
                                ch.pipeline().addLast(new ClientHandler(currentState, null));
                            }
                        });
                b.connect("127.0.0.1", 11111)
                        .addListener(new GenericFutureListener<ChannelFuture>() {
                            @Override
                            public void operationComplete(ChannelFuture future) {
                                if (future.cause() != null) {
                                    System.err.println("Could not connect,cause:" + future.cause());
                                    channel = null;
                                } else {
                                    channel = future.channel();

                                }
                                countDownLatch.countDown();
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //eventLoopGroup.shutdownGracefully();
            }
        });

        try {
            new DefaultClusterClientInitFunc().init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                try {
                    countDownLatch.await();
                    for (int i = 0; i < 30; i++) {
                        executorService.submit(testThread);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //向服务端发送内容
//                /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String content = reader.readLine();
//                int xid = getCurrentId();
//                try {
//                    startTimes.put(xid,System.currentTimeMillis());
//                    //Msg msg=new Msg(xid,content);
//                    FlowRequestData data = new FlowRequestData().setCount(1)
//                            .setFlowId(100).setPriority(false);
//                    ClusterRequest<FlowRequestData> request = new ClusterRequest<>(ClusterConstants.MSG_TYPE_FLOW, data);
//                    request.setId(xid);
//
//                    channel.writeAndFlush(request);
//
//                    ChannelPromise promise = channel.newPromise();
//                    TokenClientPromiseHolder.putPromise(xid, promise);
//
//
//                    if (!promise.await(ClusterClientConfigManager.getRequestTimeout())) {
//                        System.err.println("request time out !!!!");
//                    }
//                    AbstractMap.SimpleEntry<ChannelPromise, ClusterResponse> entry = TokenClientPromiseHolder.getEntry(xid);
//                    System.out.println("client耗时："+(System.currentTimeMillis()-startTimes.get(entry.getValue().getId())));
//                    System.out.println("client返回："+ JSON.toJSONString(entry));
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Runnable testThread = new Runnable() {
        @Override
        public void run() {
            while (true) {
                int xid = getCurrentId();
                try {
                    startTimes.put(xid, System.currentTimeMillis());
                    //Msg msg=new Msg(xid,content);
                    FlowRequestData data = new FlowRequestData().setCount(1)
                            .setFlowId(100).setPriority(false);
                    ClusterRequest<FlowRequestData> request = new ClusterRequest<>(ClusterConstants.MSG_TYPE_FLOW, data);
                    request.setId(xid);

                    channel.writeAndFlush(request);

                    ChannelPromise promise = channel.newPromise();
                    TokenClientPromiseHolder.putPromise(xid, promise);

                    long requestNum = requestCounter.incrementAndGet();
                    if (!promise.await(ClusterClientConfigManager.getRequestTimeout())) {
                        System.out.println("request num:" + requestNum);
                        System.err.println("request time out !!!!");
                    } else {
                        long successNUm = successCounter.incrementAndGet();
                        AbstractMap.SimpleEntry<ChannelPromise, ClusterResponse> entry = TokenClientPromiseHolder.getEntry(xid);
                        //System.out.println("client耗时：" + (System.currentTimeMillis() - startTimes.get(entry.getValue().getId())));
                        if (successNUm % 1000 == 0) {
                            System.out.println("success num:" + successNUm);
                            System.out.println("client耗时：" + (System.currentTimeMillis() - startTimes.get(entry.getValue().getId())));
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
    };


    private int getCurrentId() {
        if (idGenerator.get() > MAX_ID) {
            idGenerator.set(0);
        }
        return idGenerator.incrementAndGet();
    }

    private static final int MAX_ID = 999_999_999;


}
