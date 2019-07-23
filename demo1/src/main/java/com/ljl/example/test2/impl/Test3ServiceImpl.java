package com.ljl.example.test2.impl;

import com.ljl.example.common.IdWorker;
import com.ljl.example.job.TaskExecutor;
import com.ljl.example.test2.Test2Service;
import com.ljl.example.test2.Test3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by liaura_ljl on 2019/7/14.
 */
@Service
public class Test3ServiceImpl implements Test3Service{
    @Autowired
    private Test2Service test2Service;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("testThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor testThreadPoolTaskExecutor;

    @Autowired
    private IdWorker idWorker;

    public void test3(){
        test4();
        test2Service.test2();
    }

    public void test4(){
        redisTemplate.boundHashOps("hash1").put("key1","value1");
        System.out.println(redisTemplate.hasKey("hash1"));
    }

    public void test5(){
        Long taskId=idWorker.nextId();
        testThreadPoolTaskExecutor.execute(new TaskExecutor(taskId,"test") {
            @Override
            public Boolean onBefore() {
                return true;
            }

            @Override
            public Boolean onExecute() throws Exception {
                System.out.println("testThreadPoolTaskExecutor executor");
                return true;
            }

            @Override
            public Boolean onExecuteError(String msg) {
                return true;
            }

            @Override
            public Boolean onAfter() {
                return true;
            }
        });
    }

}
