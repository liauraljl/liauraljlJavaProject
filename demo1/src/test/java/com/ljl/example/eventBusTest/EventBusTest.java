package com.ljl.example.eventBusTest;

import com.ljl.example.Demo1Application;
import com.ljl.example.eventBus.service.EventBusTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class EventBusTest {

    @Autowired
    private EventBusTestService eventBusTestService;

    @Test
    public void test(){
        eventBusTestService.test1();
    }
}
