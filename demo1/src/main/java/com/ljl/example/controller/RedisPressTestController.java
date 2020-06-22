package com.ljl.example.controller;

import com.ljl.example.redis.RedisConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinlei.li
 * @date 2020/6/17 10:33
 * @description
 */
@RestController
public class RedisPressTestController implements InitializingBean {

    @Autowired
    RedisTemplate redisTemplate;

    private RedisAtomicLong ruleCounter = new RedisAtomicLong("testK2", redisTemplate);


    @RequestMapping("testSet")
    public void testSet(){
        redisTemplate.opsForValue().set("testK1",System.currentTimeMillis()/1000);
    }

    @RequestMapping("testGet")
    public void testGet(){
        redisTemplate.opsForValue().get("testK1");
    }

    @RequestMapping("testSet")
    public void testIncr(){
        ruleCounter.incrementAndGet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //ruleCounter = new RedisAtomicLong(RedisConstant.RULE_ID_KEY, redisTemplate.getConnectionFactory());

    }
}
