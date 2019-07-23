package com.ljl.example.test;

import com.ljl.example.Demo1Application;
import com.ljl.example.test2.Test2Service;
import com.ljl.example.test2.impl.Test2ServiceImpl;
import com.ljl.example.test2.impl.Test3ServiceImpl;
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



}
