package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 2 :SET ID");
        Thread.sleep(2000);
        event.setId(UUID.randomUUID().toString());
    }
}
