package com.ljl.example.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class PostConstructTest {

    @PostConstruct
    public void initTest(){
        System.out.println("启动初始化:PostConstruct");
        log.debug("启动初始化:PostConstruct");
    }
}
