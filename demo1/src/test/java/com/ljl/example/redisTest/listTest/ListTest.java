package com.ljl.example.redisTest.listTest;

import com.ljl.example.Demo1Application;
import jodd.typeconverter.Convert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class ListTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void listTest11(){
        String listKey="example:test:list1";
        List<String> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("v"+i);
        }
        redisTemplate.opsForList().leftPushAll(listKey,list);
        System.out.println("start");
        for(;;){
            try{
                System.out.println("for each start!");
                String v1= Convert.toString(redisTemplate.opsForList().rightPop(listKey,300, TimeUnit.SECONDS));
                System.out.println(v1);
                int a=1/0;
                System.out.println("for each end!");
            }catch (Exception e){
                System.out.println("空闲链接异常处理，继续执行！");
            }
        }
    }
}
