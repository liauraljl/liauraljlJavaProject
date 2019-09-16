package test1.ThreadTest.BlockingQueueTest.test2.test22;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2. RentrantLock
 *
 * Created by liaura_ljl on 2019/7/16.
 */
public class ProducerConsumer2 {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition produceCondition = lock.newCondition();
        Condition consumeCondition = lock.newCondition();

        Resource resource = new Resource(lock, produceCondition, consumeCondition);

        // 4 个生产者
        Thread p1 = new Thread(new Producer(resource, 50));
        Thread p2 = new Thread(new Producer(resource, 30));
        Thread p3 = new Thread(new Producer(resource, 40));
        Thread p4 = new Thread(new Producer(resource, 20));
        // 5 个消费者
        Thread c1 = new Thread(new Consumer(resource, 10));
        Thread c2 = new Thread(new Consumer(resource, 20));
        Thread c3 = new Thread(new Consumer(resource, 20));
        Thread c4 = new Thread(new Consumer(resource, 30));
        Thread c5 = new Thread(new Consumer(resource, 30));

        p1.start();p2.start();p3.start();p4.start();
        c1.start();c2.start();c3.start();c4.start();c5.start();

    }
}
