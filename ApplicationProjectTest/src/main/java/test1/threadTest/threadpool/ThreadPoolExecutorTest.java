package test1.threadTest.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        new ThreadPoolExecutorTest().start();
    }

    private void start() {
    }

    private ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }
}
