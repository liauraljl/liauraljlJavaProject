package test1.threadTest.producerAndConsumer.disruptorAndBest.high.multi;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by liaura_ljl on 2019/10/20.
 */
@Data
@NoArgsConstructor
public class Order {
    private String id;
    private String name;
    private double price;
}
