package test1.threadTest.blockingQueueTest.test2.test22;


/**
 * Created by liaura_ljl on 2019/7/16.
 */
public class Consumer implements Runnable {
    private Resource resource;
    private int num;

    public Consumer(Resource resource, int num) {
        this.resource = resource;
        this.num = num;
    }

    @Override
    public void run() {
        resource.remove(num);
    }
}
