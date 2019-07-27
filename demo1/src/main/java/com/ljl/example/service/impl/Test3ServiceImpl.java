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

    /**
     * Redis连接测试
     */
    public void test4(){
        String hashKey=String.format(RedisConstant.TEST_HASH,1);
        redisTemplate.boundHashOps(hashKey).put("key1","value1");
        System.out.println(redisTemplate.hasKey("hash1"));
    }

    /**
     * 线程池测试
     */
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

    /**
     * Redis锁测试
     */
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

    /**
     * 数据库连接测试
     */
    public void dbTest(){
        Example example=new Example(Process.class);
        example.createCriteria();
        List<Process> result=processMapper.selectAll();
        Process process=processMapper.selectOneByExample(example);
        System.out.println(JSON.toJSONString(process));
    }

    /**
     * 缓存读取测试
     */
    public Process readCache() {
        String key=String.format(RedisConstant.PROCESS_KEY,200003);
        if(!redisService.hasKey(key)){
            Example example=new Example(Process.class);
            example.createCriteria().andEqualTo("pid",200003L);
            List<Process> list=processMapper.selectByExample(example);
            if(list!=null&&list.size()>0){
                Process process=list.get(0);
                String redisStr=JSON.toJSONString(process);
                redisService.set(key,redisStr,120);
                return list.get(0);
            }
            return null;

        }else{
            return JSON.parseObject(redisService.get(key),Process.class);
        }
    }

    /**
     * 更新操作 保持Redis和mysql强一致性
     * @return
     */
    public Boolean updateProcessTest(){
        String key=String.format(RedisConstant.PROCESS_KEY,200003);
        //1、先删除缓存
        redisService.delKey(key);
        Example example=new Example(Process.class);
        example.createCriteria().andEqualTo("pid",200003L);
        List<Process> list=processMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            Process process=list.get(0);
            process.setProcessName(process.getProcessName()+System.currentTimeMillis());
            //2、再写数据库
            processMapper.updateByPrimaryKey(process);
            try {
                //3、休眠500毫秒
                //需要评估自己的项目的读数据业务逻辑的耗时。这么做的目的，就是确保读请求结束，写请求可以删除读请求造成的缓存脏数据。
                //当然这种策略还要考虑redis和数据库主从同步的耗时。最后的的写数据的休眠时间：则在读数据业务逻辑的耗时基础上，加几百ms即可。比如：休眠1秒。
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("InterruptedException:{}",e);
                return false;
            }
            //4、再次更新缓存
            String redisStr=JSON.toJSONString(process);
            redisService.set(key,redisStr,120);
            return true;
        }
        return false;
    }


}
