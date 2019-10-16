package test1.threadTest.producerAndConsumer.disruptorAndBest.demo.demo3;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import java.util.UUID;

public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction tradeTransaction, long l, boolean b) throws Exception {
        //这里做具体的消费逻辑
        this.onEvent(tradeTransaction);
    }

    @Override
    public void onEvent(TradeTransaction tradeTransaction) throws Exception {
        //这里做具体的消费逻辑
        tradeTransaction.setId(UUID.randomUUID().toString());//简单生成下ID
        System.out.println(tradeTransaction.getId());
    }
}
