package com.ljl.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ljl.example.Demo1Application;
import com.ljl.example.model.Process;
import com.ljl.example.redis.RedisConstant;
import com.ljl.example.redis.RedisService;
import com.ljl.example.service.other.Test2Service;
import com.ljl.example.service.other.impl.Test3ServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class Demo1ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

	@Autowired
	private Test2Service test2Service;

	@Autowired
	private Test3ServiceImpl test3Service;

	@Test
	public void test3Test() {
		test3Service.test3();
	}

	@Test
	public void test5Test(){
		test3Service.test5();
	}

	@Test
	public void test4test(){
		test3Service.test4();
	}

	@Test
	public void redisLockTest(){
		test3Service.redisLockTest();
	}

	@Test
	public void start() throws InterruptedException {
		Thread.sleep(1000000000L);
	}

	@Test
	public void processDbTest(){
		test3Service.dbTest();
	}

	@Test
	public void readCacheTest(){
		Process process=test3Service.readCache();
		System.out.println(JSON.toJSONString(process));
	}

	@Test
	public void updateProcessTest(){
		test3Service.updateProcessTest();
	}

	@Test
    public void addZset(){
	    String key= String.format(RedisConstant.TEST_ZSET_KEY1,1);
	    String value="1";
	    redisTemplate.opsForZSet().add(key,value,System.currentTimeMillis());
    }

	@Test
    public void luaTest(){
        String key= String.format(RedisConstant.TEST_ZSET_KEY1,1);
        List<String> keys=new ArrayList<>();
        keys.add(key);
        String value="1";
        redisService.redisLuaScript("lua/removeFromRedisDelayTest.lua",List.class,keys,value);
        System.out.println(JSONArray.toJSONString(""));
    }
}
