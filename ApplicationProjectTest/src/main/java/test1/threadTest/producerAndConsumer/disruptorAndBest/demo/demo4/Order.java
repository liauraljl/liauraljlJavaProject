package test1.threadTest.producerAndConsumer.disruptorAndBest.demo.demo4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;//ID
    private String name;
    private double price;//金额
}
