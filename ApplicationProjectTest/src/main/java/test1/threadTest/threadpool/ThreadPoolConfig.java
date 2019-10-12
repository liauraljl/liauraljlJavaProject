package test1.threadTest.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

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
}
