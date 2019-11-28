package test1.stringTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaura_ljl on 2018/5/22.
 */
public class StringArrSplitTest {

    public static void main(String[] args){
        String value="a,bb,ccc";
        if(value.contains(",")){
            System.err.print("1");
            String[] userArr=value.split(",");
            if(Arrays.asList(userArr).contains("bb")){
                System.out.println("bb");
            }else{
                System.out.print("c");
            }
        }

        String topics1="sds;dsfd";
        String topics2="fdsfds";
        String[] tp1=topics1.split(";");
        String[] tp2=topics2.split(";");

        Map<String, Map<Integer,Long>> mapT=new HashMap<>();
        Map<Integer,Long> map=new HashMap<>();
        mapT.put("m1",map);
        Map<Integer,Long> map2=mapT.get("m1");
        //map2.put(1,333L);
        Map<Integer, Long> currentOffset = new HashMap();
        currentOffset.put(2,44L);
        map2=currentOffset;
        try {
            Thread.sleep(1000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println();
    }
}
