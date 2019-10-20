package test1.threadTest.producerAndConsumer.disruptorAndBest.high.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by liaura_ljl on 2019/10/20.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //1、创建ringbuffer
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI,
                new EventFactory<Order>() {
                    @Override
                    public Order newInstance() {
                        return new Order();
                    }
                },
                1024 * 1024,
                new YieldingWaitStrategy());

        //2、通过ringbuffer创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        //3、创建多个消费者数组
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer("C" + i);
        }

        //4、构建多消费者工作池
        WorkerPool<Order> workerPool = new WorkerPool<Order>(ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers);

        //5、设置多个消费者的sequence序号 用于单独统计消费进度，并设置到ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        //6、启动workpool
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            Producer producer = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    latch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    producer.sendData(UUID.randomUUID().toString());
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("-----------线程创建完毕，开始生产数据-------------");
        latch.countDown();

        Thread.sleep(10000);

        System.out.println("任务总数："+consumers[0].getCount());
        System.out.println("任务总数："+consumers[1].getCount());
        System.out.println("任务总数："+consumers[2].getCount());
    }
}
