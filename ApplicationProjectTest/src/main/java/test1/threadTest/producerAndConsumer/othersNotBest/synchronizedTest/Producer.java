package test1.threadTest.producerAndConsumer.othersNotBest.synchronizedTest;

/**
 * Created by liaura_ljl on 2019/7/16.
 */
public class Producer implements Runnable {
    private Resource resource;
    private int num;

    public Producer(Resource resource, int num) {
        this.resource = resource;
        this.num = num;
    }

    @Override
    public void run() {
        resource.add(num);
    }
}
