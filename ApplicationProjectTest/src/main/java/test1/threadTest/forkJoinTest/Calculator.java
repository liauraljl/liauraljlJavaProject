package test1.threadTest.forkJoinTest;

/**
 * @author jinlei.li
 * @date 2020/7/3 10:28
 * @description
 */
public interface Calculator {
    /**
     * 把传进来的所有numbers 做求和处理
     *
     * @param numbers
     * @return 总和
     */
    long sumUp(long[] numbers);
}
