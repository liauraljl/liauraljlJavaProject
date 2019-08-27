package test1.atomicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

public class atomicLongTest {
    public static void main(String[] args) {
        List<Long> longList=new ArrayList<>();
        for(int i=1;i<10000;i++){
            longList.add((long) i);
        }
        Long totalSum =0L;
        long a=System.currentTimeMillis();
        for(int j=0;j<longList.size();j++){
            totalSum+=longList.get(j);
        }
        long b=System.currentTimeMillis();
        //AtomicReference<Long> total= new AtomicReference<>(0L);
        long c=System.currentTimeMillis();
        Long total2=longList.parallelStream().reduce(0L, (sum,item)->sum+item);
        long d=System.currentTimeMillis();
        System.out.println(totalSum +":"+(b-a));
        //System.out.println(total.get()+":"+(c-b));
        System.out.println(total2+":"+(d-c));
    }
}
