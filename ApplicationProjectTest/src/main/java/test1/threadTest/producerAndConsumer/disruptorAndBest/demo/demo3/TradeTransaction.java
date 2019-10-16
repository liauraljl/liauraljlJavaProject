package test1.threadTest.producerAndConsumer.disruptorAndBest.demo.demo3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DEMO中使用的 消息全假定是一条交易
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeTransaction {
    private String id;//交易ID
    private double price;//交易金额
}
