package test1.hashMapTest;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();//不传默认16
        Map<String,String> map2=new HashMap<>(0);//threshold=1
        Map<String,String> map3=new HashMap<>(1);//threshold=1
        Map<String,String> map31=new HashMap<>(2);//threshold=2
        Map<String,String> map4=new HashMap<>(3);//threshold=4
        Map<String,String> map5=new HashMap<>(9);//threshold=16
        Map<String,String> map6=new HashMap<>(15);//threshold=16
        Map<String,String> map7=new HashMap<>(17);//threshold=32
        Map<String,String> map8=new HashMap<>(33);//threshold=64
        Map<String,String> map9=new HashMap<>(65);//threshold=128
        map.put("s1","v1");
        map.put("s2","v2");
        map.put("s2","v2");
        map.put("s3","v3");
        map.put("s4","v4");
        map.put("s5","v5");

        map3.put("s1","v1");
        map3.put("s2","v2");
        System.out.println("");
    }
}
