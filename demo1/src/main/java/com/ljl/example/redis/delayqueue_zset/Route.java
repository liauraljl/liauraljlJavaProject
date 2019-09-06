package com.ljl.example.redis.delayqueue_zset;

import lombok.Data;

/**
 *  <p>消息路由器，主要控制将消息从指定的队列路由到待消费的list<br>
 * 通过这种方式实现自定义延迟以及优先级发送</p>
 * Created by liaura_ljl on 2019/9/6.
 */
@Data
public class Route {
    /**
     * 存放消息的队列
     */
    private String queue;

    /**
     * 待消费的列表
     */
    private String list;

    public Route(String queue, String list) {
        this.queue = queue;
        this.list = list;
    }
}
