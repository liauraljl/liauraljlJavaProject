package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class Handler1 implements EventHandler<Trade>,WorkHandler<Trade> {
    /**
     * EventHandler
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    /**
     * WorkHandler
     * @param event
     * @throws Exception
     */
    @Override
    public void onEvent(Trade event) throws Exception {
        System.out.println("handler 1 :SET NAME");
        Thread.sleep(1000);
        event.setName("H1");
    }
}
