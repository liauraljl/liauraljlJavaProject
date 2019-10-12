package test1.rateLimiterTest;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterTest {
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        final RateLimiter rateLimiter=RateLimiter.create(3.0);
        for(int i=0;i<100;i++){
            final int no=i;
            Runnable runnable=new Runnable() {
                public void run() {
                    try{
                        //获取许可
                        rateLimiter.acquire();
                        System.out.println("Accessing: " + no + ",time:"
                                + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }
        //退出线程池
        exec.shutdown();
    }
}
