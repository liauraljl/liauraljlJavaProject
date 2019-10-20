package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //构建一个线程池，用于提交任务
        ExecutorService executorService1=Executors.newFixedThreadPool(1);
        ExecutorService executorService2=Executors.newFixedThreadPool(5);

        int ringBuffer1=1024*1024;
        //1、构建Disruptor
        Disruptor<Trade> disruptor=new Disruptor<Trade>(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        },
        ringBuffer1,
        executorService2,
        ProducerType.SINGLE,
        new BusySpinWaitStrategy());

        //2、把消费者设置到Disruptor中的HandleEventsWith

        //2.1串行操作
        /*disruptor.handleEventsWith(new Handler1())
                .handleEventsWith(new Handler2())
                .handleEventsWith(new Handler3());*/

        //2.2并行操作(两种写法)
        //法1
        /*disruptor.handleEventsWith(new Handler1());
        disruptor.handleEventsWith(new Handler2());
        disruptor.handleEventsWith(new Handler3());*/
        //法2
        //disruptor.handleEventsWith(new Handler1(),new Handler2(),new Handler3());

        //2.3 菱形操作
        //方式1 1、2并行 然后-->3
        //disruptor.handleEventsWith(new Handler1(),new Handler2()).handleEventsWith(new Handler3());

        //方式2
        /*EventHandlerGroup<Trade> eventHandlerGroup=disruptor.handleEventsWith(new Handler1(),new Handler2());
        eventHandlerGroup.then(new Handler3());*/

        //2.4六边形操作
        Handler1 h1=new Handler1();
        Handler2 h2=new Handler2();
        Handler3 h3=new Handler3();
        Handler4 h4=new Handler4();
        Handler5 h5=new Handler5();
        disruptor.handleEventsWith(h1,h4);
        disruptor.after(h1).handleEventsWith(h2);
        disruptor.after(h4).handleEventsWith(h5);
        disruptor.after(h2,h5).handleEventsWith(h3);



        //3、启动disruptor
        RingBuffer<Trade> ringBuffer=disruptor.start();

        CountDownLatch latch=new CountDownLatch(1);
        long begin=System.currentTimeMillis();
        executorService1.submit(new TradePublisher(latch,disruptor));

        latch.await();
        disruptor.shutdown();
        executorService1.shutdown();
        executorService2.shutdown();
        System.out.println("总耗时:"+(System.currentTimeMillis()-begin));
    }
}
