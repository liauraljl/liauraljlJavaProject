package com.ljl.example.redis.delayqueue;

import org.redisson.Redisson;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Created by liaura_ljl on 2019/9/6.
 */
public class Producer {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBlockingDeque<Order> blockingDeque = redissonClient.getBlockingDeque("delay_queue");
        RDelayedQueue<Order> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order order = new Order();
            delayedQueue.offer(order, 1, TimeUnit.SECONDS);
            System.out.println("成功发送延时队列");
        }

        delayedQueue.destroy();
    }
}
