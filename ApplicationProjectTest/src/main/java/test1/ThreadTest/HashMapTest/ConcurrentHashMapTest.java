package test1.ThreadTest.HashMapTest;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 * Created by liaura_ljl on 2019/7/19.
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args){
        new ConcurrentHashMapTest().start();
    }

    private void start(){
        try{
            Map<String,Object> concurrentHashMap= new ConcurrentHashMap<String,Object>();
            Map<String,Object> hashMap=new HashMap<String,Object>();
            concurrentHashMap.put("key1","values");
            concurrentHashMap.put("key2",new Object());
            for(Map.Entry entry:concurrentHashMap.entrySet()){
                System.out.println(entry.getValue().toString());
            }
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        concurrentHashMap.put("1",1);
                    }
                }
            }.start();
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        concurrentHashMap.put("1",2);
                    }
                }
            }.start();
            System.out.println(concurrentHashMap.size());
            new Thread(){
                @Override
                public void run() {
                    while (true)
                        hashMap.put("3",3);
                }
            }.start();
            new Thread(){
                @Override
                public void run() {
                    while (true)
                        hashMap.put("3",4);
                }
            }.start();
            System.out.println(hashMap.size());
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
