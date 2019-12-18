package test1.threadTest.priorityQueueTest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liaura_ljl on 2019/12/18.
 */
@Data
@AllArgsConstructor
public class Cat implements Comparable<Cat> {

    private int age;

    private String name;

    @Override
    public int compareTo(Cat o) {
        return this.getAge()-o.getAge();
    }
}
