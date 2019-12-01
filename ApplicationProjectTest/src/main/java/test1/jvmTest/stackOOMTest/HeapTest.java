package test1.jvmTest.stackOOMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/12/1.
 */
public class HeapTest {
    public static void main(String[] args) {
        new HeapTest().start();
    }

    private void start(){
        //test1();
        test2();
    }

    /**
     * 演示堆内存溢出
     * java.lang.OutOfMemoryError: Java heap space
     * 参数设置：-Xms8m -Xmx8m
     */
    private void test1(){
        int count=0;
        try{
            List<String> strList=new ArrayList<>();
            String str="oomfmdsfd";
            while (true){
                strList.add(str);
                str+=str;
                count++;
            }
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }

    /**
     * 模拟内存占用
     */
    private void test2(){
        List<Cat> list=new ArrayList<>();
        try{
            for(int i=0;i<150;i++){
                Cat cat=new Cat();
                list.add(cat);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Cat{
        private byte[] cct=new byte[1024*1024];
    }
}
