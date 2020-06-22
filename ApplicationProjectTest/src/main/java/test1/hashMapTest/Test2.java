package test1.hashMapTest;

import org.joda.time.DateTime;

/**
 * @author jinlei.li
 * @date 2020/1/6 14:51
 */
public class Test2 {
    public static void main(String[] args) {
        DateTime now = DateTime.now();
        final long start = now.withSecondOfMinute(0).withMillisOfSecond(0).getMillis();
        final long end = now.getMillis();
        System.out.println("");
    }
}
