package com.ljl.example.test;

import com.alibaba.fastjson.JSON;
import com.ljl.example.Demo1Application;
import com.ljl.example.model.Process;
import com.ljl.example.service.Test2Service;
import com.ljl.example.service.impl.Test3ServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class Demo1ApplicationTests {

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
}
