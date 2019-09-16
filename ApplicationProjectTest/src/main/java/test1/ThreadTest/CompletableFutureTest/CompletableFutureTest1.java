package test1.ThreadTest.CompletableFutureTest;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest1 {
    public static void main(String[] args) {
        new CompletableFutureTest1().test1();
    }

    private void test1(){
        CompletableFuture[] completableFutures = new CompletableFuture[5];
        for(int i=0;i<5;i++){
            completableFutures[i]=CompletableFuture.supplyAsync(()->{
                return true;
            });
        }
        CompletableFuture.allOf(completableFutures).join();
        System.out.println("ddd");
        test2();
    }

    private void test2(){
        try{

        }catch (Exception e){

        }

    }
}
