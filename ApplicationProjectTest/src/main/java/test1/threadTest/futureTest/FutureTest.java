package test1.threadTest.futureTest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTest {
    private AtomicInteger count=new AtomicInteger();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new FutureTest().start();
    }

    private void start() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor=getThreadPoolExecutor();
        for(int i=0;i<20;i++){
            Future<Integer> f = threadPoolExecutor.submit(()->{
                Integer curCount=count.incrementAndGet();
                System.out.println("第\t"+curCount+"\t次执行");
                Thread.sleep(500);
                return curCount;
            });
            System.out.println("get:"+f.get());

        }
    }

    private ThreadPoolExecutor getThreadPoolExecutor(){
        return new ThreadPoolExecutor(3,5,
                //Runtime.getRuntime().availableProcessors()*10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
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
