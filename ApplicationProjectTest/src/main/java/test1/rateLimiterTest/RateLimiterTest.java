package test1.rateLimiterTest;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterTest {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        final RateLimiter rateLimiter=RateLimiter.create(9);
        AtomicInteger count=new AtomicInteger();
        CountDownLatch countDownLatch=new CountDownLatch(100);
        for(int i=0;i<100;i++){
            executorService.submit(()->{
                try{
                    //获取许可
                    rateLimiter.acquire();
                    System.out.println("Accessing: " + count.incrementAndGet() + ",time:"
                            + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //退出线程池
        executorService.shutdown();
    }
}
