package test1.threadTest.blockingQueueTest.test2.tets21;

import java.util.LinkedList;

/**
 * Created by liaura_ljl on 2019/7/16.
 */
public class Resource {
    // 队列最大数量
    private int MAX_SIZE = 100;
    // 队列产品
    private LinkedList list = new LinkedList();

    /**
     * 加入资源
     */
    public void add (int num) {
        synchronized (list) {
            System.out.print("【生产数量】：" + num + "，【队列数量】：" +
                    list.size());

            if (list.size() + num > MAX_SIZE) {
                System.out.println("，任务不执行，队列容量不足");

                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 塞入产品
            for (int i = 0; i < num; ++i) {
                list.add(new Object());
            }

            System.out.println("，任务执行，更新后【队列数量】：" + list.size());
            list.notifyAll();
        }
    }

    /**
     * 消费资源
     */
    public void remove(int num) {
        synchronized (list) {
            System.out.print("【消费数量】：" + num + "，【队列数量】：" +
                    list.size());

            if (list.size() - num < 0) {
                System.out.println("，任务不执行，队列数量不足！");

                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 取出产品
            for (int i = 0; i < num; ++i) {
                list.poll();
            }
            System.out.println("，任务执行，更新后【队列数量】：" + list.size());
            list.notifyAll();
        }
    }
}
