package test1.threadTest.synchronizedTest;

/**
 * Created by liaura_ljl on 2019/12/9.
 * 交替打印奇偶数
 */
public class ThreadTest5_交替奇偶 {

    private static final Object obj = new Object();

    private static volatile int count = 0;


    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                while (count < 100 && (count & 1) == 0) {//与1按位与
                    System.out.println(count++ + "是偶数！");
                    try {
                        obj.notify();
                        if(count<100){
                            obj.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                while (count < 100 && count % 2 == 1) {
                    System.out.println(count++ + "是奇数！");
                    try {
                        obj.notify();
                        if(count<100){
                            obj.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
