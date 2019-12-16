package test1.threadTest.synchronizedTest;

/**
 * Created by liaura_ljl on 2019/12/16.
 */
public class Test1 {
    private Object a=new Object();
    private Object b=new Object();
    private Object c=new Object();
    private Object d=new Object();

    public static void main(String[] args) {
        new Test1().start();
    }

    private void start() {
        test1();
    }

    private void test1() {
        new Thread(()->{
            synchronized (a){
                try {
                    System.out.println("wait();  before");
                    b.wait();
                    System.out.println("wait(); after");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (a){
            System.out.println("notify();  before");
            a.notify();
            System.out.println("notify();   after");
        }
    }

}
