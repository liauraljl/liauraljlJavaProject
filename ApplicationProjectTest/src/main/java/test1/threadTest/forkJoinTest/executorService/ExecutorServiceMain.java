package test1.threadTest.forkJoinTest.executorService;

import test1.threadTest.forkJoinTest.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author jinlei.li
 * @date 2020/7/3 10:33
 * @description
 */
public class ExecutorServiceMain {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result); // 打印结果500500
    }
}
