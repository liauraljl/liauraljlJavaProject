package test1.threadTest.threadSafeTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liaura_ljl on 2019/12/10.
 */
public class ThreadSafeTest1 implements Runnable {
    private int index = 0;

    private static AtomicInteger realIndex = new AtomicInteger();
    private static AtomicInteger wrongCount = new AtomicInteger();

    private static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    private static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    /**
     * 记录已加过的
     */
    private boolean[] markArr = new boolean[1000000];

    private static ThreadSafeTest1 instance = new ThreadSafeTest1();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();

                index++;

                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (markArr[index] && markArr[index - 1]) {
                    System.out.println(i);
                    wrongCount.incrementAndGet();
                }
                markArr[index] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("end:" + instance.index);
        System.out.println("real:" + realIndex.get());
        System.out.println("wrong:" + wrongCount.get());
    }
}
