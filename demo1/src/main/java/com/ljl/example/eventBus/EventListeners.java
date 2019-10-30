package com.ljl.example.eventBus;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.ljl.example.eventBus.event.TestEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现
 */
@Component
public class EventListeners {

    private EventBus eventBus;

    public EventListeners(EventBus eventBus){
        this.eventBus=eventBus;
    }

    @PostConstruct
    public void init() {
        this.eventBus.register(this);
    }

    @Subscribe
    public void onEvent(TestEvent testEvent){
        Integer code=testEvent.getCode();
        String content=testEvent.getContent();
        System.err.println(JSON.toJSONString(testEvent));
        System.out.println();
    }
}
