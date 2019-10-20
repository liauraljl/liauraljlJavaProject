package test1.threadTest.producerAndConsumer.disruptorAndBest.high.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by liaura_ljl on 2019/10/20.
 */
public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer){
        this.ringBuffer=ringBuffer;
    }

    public void sendData(String uuId){
        long sequence=ringBuffer.next();
        try{
            Order order=ringBuffer.get(sequence);
            order.setId(uuId);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
