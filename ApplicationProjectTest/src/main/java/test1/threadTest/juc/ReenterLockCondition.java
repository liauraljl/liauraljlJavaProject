package test1.threadTest.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liaura_ljl on 2019/3/1.
 */
public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    @Override
    public void run() {
        try{
            lock.lock();
            System.out.println("开始等待吧");
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tl=new ReenterLockCondition();
        Thread t1=new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        //通知线程t1继续执行
        lock.lock();
        System.out.println("我来唤醒你");
        condition.signal();
        //condition.signalAll();
        lock.unlock();
    }
}
