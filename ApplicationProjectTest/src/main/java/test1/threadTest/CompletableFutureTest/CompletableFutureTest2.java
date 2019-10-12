package test1.threadTest.completableFutureTest;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest2 {
    public static void main(String[] args) throws Exception {
        // 暂存数据
        List<String> stashList = Lists.newArrayList();
        // 任务 1：调用推荐接口获取数据
        CompletableFuture<String> t1 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T1: 获取推荐接口数据...");
                    sleepSeconds(5);
                    stashList.add("[T1 板块数据]");
                    return "[T1 板块数据]";
                });
        // 任务 2：调用搜索接口获取数据
        CompletableFuture<String> t2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2: 调用搜索接口获取数据...");
                    sleepSeconds(3);
                    return " [T2 板块数据] ";
                });
        // 任务 3：任务 1 和任务 2 完成后执行，聚合结果
        CompletableFuture<String> t3 =
                t1.thenCombine(t2, (t1Result, t2Result) -> {
                    System.out.println(t1Result + " 与 " + t2Result + "实现去重逻辑处理");
                    return "[T1 和 T2 板块数据聚合结果]";
                });
        // 等待任务 3 执行结果
        System.out.println(t3.get(6, TimeUnit.SECONDS));
    }

    static void sleepSeconds(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
