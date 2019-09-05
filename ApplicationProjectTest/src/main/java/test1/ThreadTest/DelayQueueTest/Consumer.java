package test1.ThreadTest.DelayQueueTest;

import java.util.concurrent.DelayQueue;

/**
 * Created by liaura_ljl on 2019/9/5.
 */
public class Consumer implements Runnable {
    // 延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<Message> queue;
    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Message take = queue.take();
                System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
