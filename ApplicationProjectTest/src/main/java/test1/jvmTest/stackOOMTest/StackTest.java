package test1.jvmTest.stackOOMTest;

/**
 * Created by liaura_ljl on 2019/12/1.
 */
public class StackTest {

    private static int test1Count1=0;

    public static void main(String[] args) {
        new StackTest().start();
    }

    private void start(){
        //test1();
        test2();
    }

    /**
     * 模拟虚拟机栈内存溢出  StackOverflowError
     * VM options:-Xss128k
     */
    private void test1(){
        try{
            //method1();
            method12();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(test1Count1);
        }
    }

    private void method1(){
        test1Count1++;
        method1();
    }

    private void method12(){
        test1Count1++;
        method13();
    }

    private void method13(){
        test1Count1++;
        method12();
    }

    private void test2(){
        while (true){
            long a=2;
            double b=2.99;
        }
    }
}
