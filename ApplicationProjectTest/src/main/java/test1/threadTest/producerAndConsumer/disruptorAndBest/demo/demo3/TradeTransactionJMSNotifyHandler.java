package test1.threadTest.producerAndConsumer.disruptorAndBest.demo.demo3;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction tradeTransaction, long l, boolean b) throws Exception {
        //do send jms message
    }
}
