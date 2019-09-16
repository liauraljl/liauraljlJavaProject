package test1.ThreadTest.BlockingQueueTest.test2.test23;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liaura_ljl on 2019/7/16.
 */
public class Resource {
    final int MAX_SIZE=100;
    private BlockingQueue queue=new ArrayBlockingQueue(MAX_SIZE);

    /**
     * 生产资源
     */
    public void add(int num){
        try{
            for (int i=0;i<num;i++){
                queue.put(new Object());
            }
            System.out.println("【生产数量】:"+num+",任务执行，更新后【队列数量】:"+queue.size());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 消费资源
     */
    public void remove(int num){
        try{
            for(int i=0;i<num;i++){
                queue.take();
            }
            System.out.println("【消费数量】:"+num+",任务执行,更新后【队列数量】:"+queue.size());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
