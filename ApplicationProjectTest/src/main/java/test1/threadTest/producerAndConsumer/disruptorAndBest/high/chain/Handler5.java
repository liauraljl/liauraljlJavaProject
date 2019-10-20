package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import com.lmax.disruptor.EventHandler;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class Handler5 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 5 :GET IPRICE:"+event.getPrice());
        event.setPrice(event.getPrice()+3.0);
    }
}
