package test1.threadTest.delayOrderQueueManagerTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by liaura_ljl on 2019/9/6.
 */
public class Test {
    public static void main(String[] args) {
        DelayOrderWorker work1 = new DelayOrderWorker();// 任务1
        DelayOrderWorker work2 = new DelayOrderWorker();// 任务2
        DelayOrderWorker work3 = new DelayOrderWorker();// 任务3
        // 延迟队列管理类，将任务转化消息体并将消息体放入延迟对列中等待执行
        DelayOrderQueueManager manager = DelayOrderQueueManager.getInstance();
        manager.put(work1, 3000, TimeUnit.MILLISECONDS);
        manager.put(work2, 6000, TimeUnit.MILLISECONDS);
    }
}
