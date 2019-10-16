package com.ljl.example.sentimel;

import com.ljl.example.Demo1Application;
import com.ljl.example.sentinel.SentinelTestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liaura_ljl on 2019/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class SentinelTest {

    @Autowired
    private SentinelTestServiceImpl sentinelTestServiceImpl;

    @Test
    public void test1(){
        while (true) {
            sentinelTestServiceImpl.helloWorld();
        }
    }

    @Test
    public void test2() throws InterruptedException {
        for (int i=0;i<10000;i++){
            sentinelTestServiceImpl.test("第"+(i+1)+"次调用");
        }
    }
}
