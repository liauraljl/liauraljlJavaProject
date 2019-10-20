package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

/**
 * Created by liaura_ljl on 2019/10/19.
 */
public class TradeEventTranslator implements EventTranslator<Trade> {
    private Random random=new Random();
    @Override
    public void translateTo(Trade event, long sequence) {
        this.generateTrade(event);
    }

    private void generateTrade(Trade event){
        event.setPrice(random.nextDouble()*9999);
    }
}
