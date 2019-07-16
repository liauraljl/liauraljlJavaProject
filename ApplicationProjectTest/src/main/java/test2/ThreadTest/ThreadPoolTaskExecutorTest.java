package test2.ThreadTest;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolTaskExecutorTest {
    public static void main(String[] args) {

        System.out.println("111");
        new ThreadPoolTaskExecutor().execute(new PrintJobTest1());
        System.out.println("222");
    }
}
