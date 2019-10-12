package test1.threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liaura_ljl on 2019/9/1.
 */
public class CallableTest {
    public static void main(String[] args){
        int a=1;
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return a+1;
            }
        };
        Future<Integer> future= Executors.newFixedThreadPool(1).submit(callable);
        Integer result=0;
        try {
            result=future.get();
            //Integer result=future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result="+result);
    }
}
