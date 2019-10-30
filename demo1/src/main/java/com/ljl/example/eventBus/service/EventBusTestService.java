package com.ljl.example.eventBus.service;

import com.google.common.eventbus.EventBus;
import com.ljl.example.eventBus.event.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventBusTestService {

    @Autowired
    private EventBus eventBus;

    public void test1(){
        TestEvent testEvent=new TestEvent(111,"content1");
        eventBus.post(testEvent);
    }
}
