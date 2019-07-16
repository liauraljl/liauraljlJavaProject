package test.BlockingQueueTest.test2.test23;


/**
 * 3、BlockingQueue
 *
 * Created by liaura_ljl on 2019/7/16.
 */
public class ProducerConsumer3 {
    public static void main(String[] args){
        Resource resource=new Resource();

        //4个生产者
        Thread p1=new Thread(new Producer(resource,50));
        Thread p2=new Thread(new Producer(resource,40));
        Thread p3=new Thread(new Producer(resource,30));
        Thread p4=new Thread(new Producer(resource,20));

        //5个消费者
        Thread c1=new Thread(new Consumer(resource,10));
        Thread c2=new Thread(new Consumer(resource,20));
        Thread c3=new Thread(new Consumer(resource,20));
        Thread c4=new Thread(new Consumer(resource,30));
        Thread c5=new Thread(new Consumer(resource,30));

        p1.start();p2.start();p3.start();p4.start();
        c1.start();c2.start();c3.start();c4.start();c5.start();
    }
}
