package test1.threadTest.producerAndConsumer.disruptorAndBest;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer){
        this.ringBuffer=ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        long sequence=ringBuffer.next();
        try{
            PCData event=ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
