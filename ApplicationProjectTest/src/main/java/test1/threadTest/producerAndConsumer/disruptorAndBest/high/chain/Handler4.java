package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;


import com.lmax.disruptor.EventHandler;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class Handler4 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("handler 4 :SET IPRICE");
        event.setPrice(17.0);
    }
}
