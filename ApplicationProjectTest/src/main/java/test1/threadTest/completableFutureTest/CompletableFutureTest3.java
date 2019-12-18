package test1.threadTest.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest3 {

    private static Integer calc(Integer para){
        return para/2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> inFuture1=CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> inFuture2=CompletableFuture.supplyAsync(()->calc(25));
        CompletableFuture<Void> fu=inFuture1.thenCombine(inFuture2,(i,j)->(i+j))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
