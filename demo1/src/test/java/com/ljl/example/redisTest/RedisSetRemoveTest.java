package com.ljl.example.redisTest;

import com.ljl.example.Demo1Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class RedisSetRemoveTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void addSetTest() {
        String key = "test:zset:1";
        for (int i = 0; i < 10; i++) {
            redisTemplate.opsForZSet().add(key, Integer.valueOf(i), System.currentTimeMillis());
        }
    }

    @Test
    public void removeTest(){
        String key = "test:zset:1";
        Set<ZSetOperations.TypedTuple<Integer>> set=redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
        for(ZSetOperations.TypedTuple<Integer> zSetModel:set){
            if(((Integer)zSetModel.getValue()).equals(3))
                redisTemplate.opsForZSet().remove(key,zSetModel.getValue());
        }
        for(ZSetOperations.TypedTuple<Integer> zSetModel:set){
            System.out.println(zSetModel.getValue());
        }
        Set<ZSetOperations.TypedTuple<Integer>> set2=redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
        for(ZSetOperations.TypedTuple<Integer> zSetModel:set2){
            System.out.println(zSetModel.getValue());
        }
        System.out.println("");
    }

    @Test
    public void removeTest2(){
        String key = "test:zset:1";
        Set<ZSetOperations.TypedTuple<Integer>> set=redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
        Iterator<ZSetOperations.TypedTuple<Integer>> iterator=set.iterator();
        while (iterator.hasNext()){
            ZSetOperations.TypedTuple<Integer> zSetModel=iterator.next();
            if(((Integer)zSetModel.getValue()).equals(4))
                iterator.remove();//删除集合并未删除元素
        }
        for(ZSetOperations.TypedTuple<Integer> zSetModel:set){
            System.out.println(zSetModel.getValue());
        }
        Set<ZSetOperations.TypedTuple<Integer>> set2=redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
        for(ZSetOperations.TypedTuple<Integer> zSetModel:set2){
            System.out.println(zSetModel.getValue());
        }
        System.out.println("");


    }
}
