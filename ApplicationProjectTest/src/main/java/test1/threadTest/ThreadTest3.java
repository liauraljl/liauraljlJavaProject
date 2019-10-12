package test1.threadTest;

import java.util.concurrent.*;

/**
 * Created by liaura_ljl on 2018/12/9.
 */
public class ThreadTest3 implements Runnable {

    private volatile int i=0;
    public  static void main(String[] args){
        new ThreadTest3().test();
        //Thread t1=new Thread(new ThreadTest3());
        //t1.start();
    }

    @Override
    public void run(){
        while (true){
            System.out.println("Oh,I am Runnable!");
        }
    }

    private void test(){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(;i<10;i++){
            try{
                System.out.println("start:"+i);
                Future<Integer> future=executorService.submit(() -> {
                    Thread.sleep(1000);
                    System.out.println(i + "");
                    return Integer.valueOf(i);
                });
                System.out.println("end:"+i);
                System.out.println("return:"+future.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
