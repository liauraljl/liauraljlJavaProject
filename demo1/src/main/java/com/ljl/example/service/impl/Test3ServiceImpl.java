package com.ljl.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.ljl.example.common.IdWorker;
import com.ljl.example.common.RedisConstant;
import com.ljl.example.job.TaskExecutor;
import com.ljl.example.mapper.ProcessMapper;
import com.ljl.example.model.Process;
import com.ljl.example.service.Test2Service;
import com.ljl.example.service.Test3Service;
import com.ljl.example.util.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by liaura_ljl on 2019/7/14.
 */
@Slf4j
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private ProcessMapper processMapper;

    public void test3(){
        test2Service.test2();
    }

    public void test4(){
        String hashKey=String.format(RedisConstant.TEST_HASH,1);
        redisTemplate.boundHashOps(hashKey).put("key1","value1");
        System.out.println(redisTemplate.hasKey("hash1"));
    }

    public void test5(){
        testThreadPoolTaskExecutor.execute(() -> System.out.println("mapper executor!"));
        Long taskId=idWorker.nextId();
        testThreadPoolTaskExecutor.execute(new TaskExecutor(taskId, "mapper") {
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

    public void redisLockTest(){
        String lockKey=String.format(RedisConstant.TEST_LOCK,1);
        try {
            if(redisService.getLock(lockKey)){
                System.out.println("获取到redis锁");
                log.info("get redis lock:{} successful",lockKey);
            }else{
                System.out.println("未获取到Redis锁");
                log.info("failed to get redis lock:{}",lockKey);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("get redis lock:{} error",lockKey);
        }finally {
            redisService.unLock(lockKey);
        }
    }

    public void dbTest(){
        Example example=new Example(Process.class);
        example.createCriteria();
        List<Process> result=processMapper.selectAll();
        Process process=processMapper.selectOneByExample(example);
        System.out.println(JSON.toJSONString(process));
    }

}
