package com.ljl.example.redis.delayqueue;

import org.redisson.Redisson;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaura_ljl on 2019/9/6.
 */
public class Consumer {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBlockingDeque<Order> blockingDeque = redissonClient.getBlockingDeque("delay_queue");

        while (true) {
            Order order = null;
            try {
                // 如果没有到期消息，返回null
                order = blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (order != null)
                System.out.println("订单取消时间：" + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "==订单生成时间" + order.getCreatedTime());
            else
                continue;
        }

    }
}
