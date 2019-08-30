package test1.ThreadTest;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by liaura_ljl on 2019/3/1.
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+":Thread ID:"+ Thread.currentThread().getId());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyTask task=new MyTask();
        ExecutorService es= Executors.newFixedThreadPool(6);
        /*Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newWorkStealingPool();*/
        //es.
        /*ThreadPoolExecutor;
        ThreadPoolTaskExecutor;*/

        for(int i=0;i<10;i++){
            es.execute(task);
        }
        for(int i=0;i<10;i++){
            es.submit(task);
        }
    }
}
