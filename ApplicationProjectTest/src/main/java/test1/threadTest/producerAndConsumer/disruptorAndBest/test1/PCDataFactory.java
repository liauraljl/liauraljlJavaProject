package test1.threadTest.producerAndConsumer.disruptorAndBest.test1;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
