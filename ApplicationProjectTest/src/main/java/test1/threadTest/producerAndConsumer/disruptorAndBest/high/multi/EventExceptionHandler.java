package test1.threadTest.producerAndConsumer.disruptorAndBest.high.multi;

import com.lmax.disruptor.ExceptionHandler;

/**
 * Created by liaura_ljl on 2019/10/20.
 */
public class EventExceptionHandler implements ExceptionHandler<Order> {
    @Override
    public void handleEventException(Throwable ex, long sequence, Order event) {

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
