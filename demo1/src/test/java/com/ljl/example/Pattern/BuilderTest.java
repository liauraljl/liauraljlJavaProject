package com.ljl.example.Pattern;

import com.alibaba.fastjson.JSON;
import com.ljl.example.Demo1Application;
import com.ljl.example.pattern.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class BuilderTest {

    @Test
    public void test1(){
        User user=User.builder().name("Miss li").age(25).build();
        user=user.toBuilder().age(27).build();
        System.out.println(JSON.toJSONString(user));

        User user2= User.builder().build();
        //User
    }
}
