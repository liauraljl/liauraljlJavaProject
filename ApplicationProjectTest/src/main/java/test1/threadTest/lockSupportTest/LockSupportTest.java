package test1.threadTest.lockSupportTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
<<<<<<< HEAD
        CountDownLatch latch=new CountDownLatch(1);
=======
        CountDownLatch countDownLatch=new CountDownLatch(1);
>>>>>>> 3b28c3fa90aa9ee0e2421bd0b545f0b45cc42f3a
        Thread parkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park begin");

                //等待获取许可
                LockSupport.park();
<<<<<<< HEAD

=======
                countDownLatch.countDown();
>>>>>>> 3b28c3fa90aa9ee0e2421bd0b545f0b45cc42f3a
                //输出thread over.true
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });
        parkThread.start();

        Thread.sleep(2000);
        // 中断线程
<<<<<<< HEAD
        latch.await();
=======
        countDownLatch.await();
>>>>>>> 3b28c3fa90aa9ee0e2421bd0b545f0b45cc42f3a
        parkThread.interrupt();

        System.out.println("main over");
    }
}
