package com.ljl.example.redis.delayqueue_zset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>消息队列配置</p>
 * Created by liaura_ljl on 2019/9/6.
 */
@Configuration
public class MqConfig {

    @Bean(name = "redisMQ")
    @Primary
    public RedisMQ getRedisMq() {
        RedisMQ redisMQ = new RedisMQ();
        // 配置监听队列元素数量
        redisMQ.setMonitorCount(monitorCount);
        // 配置路由表
        redisMQ.setRoutes(routeList());
        return redisMQ;
    }

    /**
     * 返回路由表
     * @return
     */
    public List<Route> routeList() {
        List<Route> routeList = new ArrayList<>();
        Route routeFirst = new Route(queueFirst, listFirst);
        Route routeSecond = new Route(queueSecond, listSecond);
        routeList.add(routeFirst);
        routeList.add(routeSecond);
        return routeList;
    }

    @Value("${mq.monitor.count:30}")
    private int monitorCount;
    @Value("${mq.queue.first:1}")
    private String queueFirst;
    @Value("${mq.queue.second:2}")
    private String queueSecond;
    @Value("${mq.consumer.first:1}")
    private String listFirst;
    @Value("${mq.consumer.second:2}")
    private String listSecond;
}
