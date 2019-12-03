package test1.jvmTest.stringTableTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaura_ljl on 2019/12/2.
 */
public class StringTableTest {


    public static void main(String[] args) {
        new StringTableTest().start();
    }

    private void start(){
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }

    /**
     * StringTable 测试
     * javap -v class文件 ==>反编译字节码指令
     */
    private void test1(){
        String s1="a";//字符串"a"放入StringTable
        String s2="b";//字符串"b"放入StringTable
        String s3="ab";//字符串"ab"放入StringTable
        String s4=s1+s2;//new StringBuilder().append("a").append("b").toString();StringBuilder的toString==> new String("ab")放入堆中

        String s5="a"+"b";//Javac在编译期的优化，结果已在编译期间确定为"ab",s1 s2为变量 可能变化
        String s6=new String("a")+new String("b");

        String s7=new String("a")+new String("b");//常量"a","b"放入串池,new String("a"),new String("b"),new String("ab")放入堆中
        String s8=s7.intern();//将这个字符串尝试放入串池，有则不放入，没有则不放入,会把串池中的对象返回
        String s9=s4.intern();//将这个字符串尝试放入串池，有则不放入，没有则不放入,会把串池中的对象返回

        System.out.println("s3==s4:"+(s3==s4));
        System.out.println("s3==s5:"+(s3==s5));
        System.out.println("s4==s5:"+(s4==s5));
        System.out.println();

        System.out.println("s5==s6:"+(s5==s6));
        System.out.println("s3==s6:"+(s3==s6));
        System.out.println("s4==s6:"+(s4==s6));
        System.out.println();

        System.out.println("s8==s3:"+(s8==s3));
        System.out.println("s8==s4:"+(s8==s4));
        System.out.println("s9==s3:"+(s9==s3));
        System.out.println();
    }

    /**
     * 延迟加载
     */
    private void test2(){
        System.out.println("0");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
    }

    private void test3(){
        String s=new String("a")+new String("b");//常量"a","b"放入串池,new String("a"),new String("b"),new String("ab")放入堆中
        String s2=s.intern();//将这个字符串尝试放入串池，有则不放入，没有则不放入,会把串池中的对象返回

    }

    /**
     * 演示StringTable的位置
     */
    private void test4(){
        List<String> list=new ArrayList<>();
        int i=0;
        try{
            for(int j=0;j<260000;j++){
                list.add(String.valueOf(j).intern());
                i++;
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }
    }

    /**
     * 演示StringTable 垃圾回收
     * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
     */
    private void test5(){
        int i=0;
        try{
            for(int j=0;j<10000;j++){
                String.valueOf(j).intern();
                i++;
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }

    }
}
