package test1.threadTest.juc;

import test1.threadTest.exceptionTest.MyUncaughtExceptionHandler;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        new CyclicBarrierTest().start();
    }

    private void start() throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
        Thread t=new Thread(()->{
            try {
                System.out.println("start");
                cyclicBarrier.await();
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        t.start();
        Thread.sleep(3000);
        System.out.println("reset");
        System.err.println(cyclicBarrier.getNumberWaiting());
        //cyclicBarrier.reset();
        System.err.println(cyclicBarrier.getNumberWaiting());
        cyclicBarrier.await();
        Thread.sleep(100000000);

    }
}
