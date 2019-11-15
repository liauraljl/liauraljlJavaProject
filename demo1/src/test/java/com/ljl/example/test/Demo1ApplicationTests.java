package com.ljl.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ljl.example.Demo1Application;
import com.ljl.example.model.Process;
import com.ljl.example.redis.RedisConstant;
import com.ljl.example.redis.RedisService;
import com.ljl.example.service.componentTest.impl.Test3ServiceImpl;
import com.ljl.example.service.other.Test2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
	private Test2Service s222222222111111;

	/*@Resource
	private Test3ServiceImpl mytest3ServiceImpl;*/

	@Autowired
	private Test3ServiceImpl mytest3ServiceImpl;

	@Test
	public void test3Test() {
		mytest3ServiceImpl.test3();
	}

	@Test
	public void test2(){
		for(int i=0;i<100000;i++){
			try{
				String result=s222222222111111.test2();
				if(!result.equals("success"))
					System.err.println(result);
			}catch (Exception e){

			}
		}
		System.out.println("执行完毕，没有降级！！！！！！！！！！！！");
	}

	/*@Test
	public void test5Test(){
		mytest3ServiceImpl.test5();
	}

	@Test
	public void test4test(){
		mytest3ServiceImpl.test4();
	}

	@Test
	public void redisLockTest(){
		mytest3ServiceImpl.redisLockTest();
	}

	@Test
	public void start() throws InterruptedException {
		Thread.sleep(1000000000L);
	}

	@Test
	public void processDbTest(){
		mytest3ServiceImpl.dbTest();
	}

	@Test
	public void readCacheTest(){
		Process process=mytest3ServiceImpl.readCache();
		System.out.println(JSON.toJSONString(process));
	}

	@Test
	public void updateProcessTest(){
		mytest3ServiceImpl.updateProcessTest();
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
    }*/
}
