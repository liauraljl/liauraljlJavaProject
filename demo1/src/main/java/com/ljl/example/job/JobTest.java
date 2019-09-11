package com.ljl.example.job;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.*;

@Slf4j
@Component
public class JobTest {

    @Autowired
    @Qualifier("testThreadPoolTaskExecutor2")
    ThreadPoolTaskExecutor testThreadPoolTaskExecutor2;

    //@Scheduled(cron = "*/20 * * * * ?")
    public void jobTest(){
        System.out.println("job executor start!"+ JSON.toJSONString(new Date()));
        int a=1;
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return a+1;
            }
        };
        Future<Integer> future=testThreadPoolTaskExecutor2.submit(callable);
        Integer result=0;
        try {
            result=future.get();
            //Integer result=future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("job job executor end! result="+result);
    }

}
