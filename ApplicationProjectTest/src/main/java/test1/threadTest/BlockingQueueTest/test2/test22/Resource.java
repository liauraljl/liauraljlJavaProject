package test1.threadTest.blockingQueueTest.test2.test22;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by liaura_ljl on 2019/7/16.
 */
public class Resource {
    // 队列最大数量
    private int MAX_SIZE = 100;
    // 队列产品
    private LinkedList list = new LinkedList();

    private Lock lock;
    private Condition produceCondition;
    private Condition consumeCondition;

    public Resource(Lock lock, Condition condition1, Condition condition2) {
        this.lock = lock;
        this.produceCondition = condition1;
        this.consumeCondition = condition2;
    }

    /**
     * 加入资源
     */
    public void add (int num) {
        lock.lock();
        try {
            while (list.size() + num > MAX_SIZE) {
                System.out.println("【生产数量】：" + num + "，【队列数量】：" +
                        list.size() + "，任务暂不执行，进入等待！");
                try {
                    produceCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 塞入产品
            for (int i = 0; i < num; ++i) {
                list.add(new Object());
            }
            System.out.println("【生产数量】：" + num + "，任务执行，更新后【队列数量】：" + list.size());
            consumeCondition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 消费资源
     */
    public void remove(int num) {
        lock.lock();
        try {
            while (list.size() - num < 0) {
                System.out.println("【消费数量】：" + num + "，【队列数量】：" +
                        list.size() + "，任务暂不执行，任务进入等待！");
                try {
                    consumeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 取出产品
            for (int i = 0; i < num; ++i) {
                list.poll();
            }
            System.out.println("【消费数量】：" + num + "，任务执行，更新后【队列数量】：" + list.size());
            produceCondition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}
