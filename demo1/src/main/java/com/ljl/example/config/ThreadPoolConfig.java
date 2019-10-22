package com.ljl.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean("testThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor testThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(400);
        threadPoolTaskExecutor.setMaxPoolSize(150);
        threadPoolTaskExecutor.setKeepAliveSeconds(180);
        threadPoolTaskExecutor.setThreadNamePrefix("testThreadPoolTaskExecutor");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean("testThreadPoolTaskExecutor2")
    public ThreadPoolTaskExecutor testThreadPoolTaskExecutor2(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(400);
        threadPoolTaskExecutor.setMaxPoolSize(150);
        threadPoolTaskExecutor.setKeepAliveSeconds(180);
        threadPoolTaskExecutor.setThreadNamePrefix("testThreadPoolTaskExecutor2");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean("testThreadPoolExecutor")
    public ThreadPoolExecutor testThreadPoolExecutor(){
        return new ThreadPoolExecutor(5,
                Runtime.getRuntime().availableProcessors()*10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(200),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("order-thread");
                        if (t.isDaemon()) {
                            t.setDaemon(false);
                        }
                        if (Thread.NORM_PRIORITY != t.getPriority()) {
                            t.setPriority(Thread.NORM_PRIORITY);
                        }
                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("拒绝策略："+r);
                    }
                });
    }
}
