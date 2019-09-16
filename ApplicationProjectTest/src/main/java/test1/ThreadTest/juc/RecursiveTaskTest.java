package test1.ThreadTest.juc;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by liaura_ljl on 2019/9/16.
 */
public class RecursiveTaskTest extends RecursiveTask<Long> {
    private static final int THRESHOLD=10000;
    private long start;
    private long end;

    public RecursiveTaskTest(long start,long end){
        this.start=start;
        this.end=end;
    }

    @Override
    protected Long compute() {
        long sum=0;
        boolean canCompute=(end-start)<THRESHOLD;
        if(canCompute){
            for (long i=start;i<=end;i++){
                sum+=i;
            }
        }else {
            //分成100个任务
            long step=(start+end)/100;
            ArrayList<RecursiveTaskTest> subTasks=new ArrayList<RecursiveTaskTest>();
            long pos=start;
            for(int i=0;i<100;i++){
                long lastOne=pos+step;
                if(lastOne>end)lastOne=end;
                RecursiveTaskTest subTask=new RecursiveTaskTest(pos,lastOne);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for(RecursiveTaskTest t:subTasks){
                sum+=t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        RecursiveTaskTest task=new RecursiveTaskTest(0,200000L);
        ForkJoinTask<Long> result=forkJoinPool.submit(task);
        try{
            long res= 0;
            res = result.get();
            System.out.println("sum="+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
