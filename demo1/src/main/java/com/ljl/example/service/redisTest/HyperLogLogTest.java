package com.ljl.example.service.redisTest;

import com.alibaba.fastjson.JSONArray;
import com.ljl.example.redis.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class HyperLogLogTest {

    @Autowired
    private RedisTemplate redisTemplate;

    public void HyerLogLogTest(){
        List<String> userList=new ArrayList<>();
        for(int i=0;i<10000;i++){
            userList.add("user"+(i+1));
        }
        String hyperLogLogKey=String.format(RedisConstant.TEST_HYPERLOGLOG_KEY1,1);
        long a=System.currentTimeMillis();
        redisTemplate.executePipelined((RedisCallback<List<Object>>) redisConnection -> {
            redisConnection.openPipeline();
            for(String userId:userList){
                redisConnection.pfAdd(hyperLogLogKey.getBytes(StandardCharsets.UTF_8),userId.getBytes(StandardCharsets.UTF_8));
            }
            redisConnection.closePipeline();
            return null;
        });
        long b=System.currentTimeMillis();
        long c=b-a;

        System.out.println(JSONArray.toJSONString(c));
    }
}
