package test1.threadTest.priorityQueueTest;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by liaura_ljl on 2019/12/18.
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        new PriorityQueueTest().start();
    }

    private void start() {
        PriorityBlockingQueue<Cat> priorityQueue=new PriorityBlockingQueue<>(5,Cat::compareTo);
        Cat cat1=new Cat(5,"name5");
        Cat cat2=new Cat(7,"name7");
        Cat cat3=new Cat(1,"name1");
        Cat cat4=new Cat(6,"name6");

        priorityQueue.add(cat1);
        priorityQueue.add(cat2);
        priorityQueue.add(cat3);
        priorityQueue.add(cat4);
        for(int i=0;i<4;i++){
            Cat cat=priorityQueue.poll();
            System.out.println(cat);
        }
    }

}
