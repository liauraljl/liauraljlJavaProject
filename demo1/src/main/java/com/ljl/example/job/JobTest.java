package com.ljl.example.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobTest {

    @Scheduled(cron = "*/20 * * * * ?")
    public void jobTest(){
        System.out.println("job executor!");
    }
}
