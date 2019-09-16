package test1.ThreadTest.BlockingQueueTest.test1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 * Created by liaura_ljl on 2019/7/15.
 */
public class Producer implements Runnable{

    private volatile boolean isRunning=true;//是否在运行标志
    private BlockingQueue queue;//阻塞队列
    private static AtomicInteger count=new AtomicInteger();//自动跟新的值
    private static final int DEFAULT_RANGE_FOR_SLEEP=1000;

    public Producer(BlockingQueue queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        String data=null;
        Random r=new Random();

        System.out.println("启动生产者线程！");
        try{
            while (isRunning){
               System.out.println("正在生产数据...");
               Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));//取DEFAULT_RANGE_FOR_SLEEP值得一个随机数

                data="data:"+count.incrementAndGet();//以原子方式将count当前值加1
                System.out.println("将数据：" + data + "放入队列...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {//设定的等待时间为2s，如果超过2s还没加进去返回true
                    System.out.println("放入数据失败：" + data);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }
}
