package test1.threadTest.producerAndConsumer.disruptorAndBest.high.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liaura_ljl on 2019/10/20.
 */
public class Consumer implements WorkHandler<Order> {
    private String consumerId;

    private static AtomicInteger count=new AtomicInteger(0);

    private Random random=new Random();

    public Consumer(String consumerId){
        this.consumerId=consumerId;
    }
    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(1*random.nextInt(5));
        System.out.println("当前消费者:"+this.consumerId+",消费信息id:"+event.getId());
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }
}
