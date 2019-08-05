package test1.StreamTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SkipLimitTest1 {
    public static void main(String[] args) {
        new SkipLimitTest1().start();
    }

    private void start() {
        List<Integer> lst = new ArrayList<Integer>();
        for (int i = 1; i < 101; i++) {
            lst.add(i);
        }
        int forCounts = (99 / 3) + 1;
        for (int i = 0; i < forCounts; i++) {
            List<Integer> tourSkip = lst.stream().skip(i * 3).limit(3).parallel().collect(Collectors.toList());
            tourSkip.forEach(o -> System.out.println(o));
        }
        System.out.println("");
    }
}
