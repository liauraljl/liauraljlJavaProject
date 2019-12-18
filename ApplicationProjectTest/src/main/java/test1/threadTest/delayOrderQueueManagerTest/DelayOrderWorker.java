package test1.threadTest.delayOrderQueueManagerTest;

/**
 * 具体执行相关业务的业务类
 * Created by liaura_ljl on 2019/9/6.
 */
public class DelayOrderWorker implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        //相关业务逻辑处理
        System.out.println(Thread.currentThread().getName()+" do something ……");
    }
}
