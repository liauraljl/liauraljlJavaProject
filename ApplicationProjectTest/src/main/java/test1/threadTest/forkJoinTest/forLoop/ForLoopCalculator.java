package test1.threadTest.forkJoinTest.forLoop;

import test1.threadTest.forkJoinTest.Calculator;

/**
 * @author jinlei.li
 * @date 2020/7/3 10:28
 * @description
 */
public class ForLoopCalculator implements Calculator {

    @Override
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}
