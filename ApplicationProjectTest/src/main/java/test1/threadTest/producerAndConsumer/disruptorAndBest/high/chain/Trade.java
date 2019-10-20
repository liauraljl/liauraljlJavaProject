package test1.threadTest.producerAndConsumer.disruptorAndBest.high.chain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Disruptor中的Event
 */
@Data
@NoArgsConstructor
public class Trade {
    private String id;
    private String name;
    private double price;
    private AtomicInteger count=new AtomicInteger(0);
}
