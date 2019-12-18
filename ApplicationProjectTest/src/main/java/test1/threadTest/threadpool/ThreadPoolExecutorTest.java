package test1.threadTest.threadpool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

    private ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        //threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setThreadNamePrefix("testThreadPoolTaskExecutor");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    public static void main(String[] args) {
        new ThreadPoolExecutorTest().start();
    }

    private void start() {
        //test1();
        test2();
    }

    private void test2() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=getThreadPoolTaskExecutor();
        ListenableFuture<String> listenableFuture= threadPoolTaskExecutor.submitListenable(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success!";
        });
        try {
            CompletableFuture completableFuture=listenableFuture.completable();
            listenableFuture.addCallback(str -> callBack(str),ex->ex.printStackTrace());
            //String ss=listenableFuture.get();
            //System.out.println(ss);
            Thread.sleep(1000000);
            System.out.println("sq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void callBack(String as){
        System.out.println("这里是回调的："+as);
    }

    private void test1(){
        ThreadPoolExecutor threadPoolExecutor=getThreadPoolExecutor();
        Future<String> future= threadPoolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "success!";
            }
        });
        try {
            String ss=future.get();
            System.out.println(ss);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
