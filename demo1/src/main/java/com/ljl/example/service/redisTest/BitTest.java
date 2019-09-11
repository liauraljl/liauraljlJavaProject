package com.ljl.example.service.redisTest;

import com.ljl.example.redis.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BitTest {

    @Autowired
    private RedisTemplate redisTemplate;

    public void bitTest1(){
        String bitKey= String.format(RedisConstant.TEST_BIT_KEY1,1);
        redisTemplate.opsForValue().setBit(bitKey,1,true);
        Boolean first=redisTemplate.opsForValue().getBit(bitKey,1);
        redisTemplate.opsForValue().setBit(bitKey,2,false);
        Boolean second=redisTemplate.opsForValue().getBit(bitKey,2);
        redisTemplate.opsForValue().setBit(bitKey,2,true);
        Boolean third=redisTemplate.opsForValue().getBit(bitKey,2);
        Boolean third2=redisTemplate.opsForValue().getBit(bitKey,3);
        //redisTemplate.opsForValue().bitField(bitKey).get()
    }
}
