package test1.threadTest.synchronizedTest;

/**
 * Created by liaura_ljl on 2019/12/9.
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        /*Thread t1 = new Thread();
        t1.start();
        //t1.start();//会检查并修改线程状态  只能调用一次
        t1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.isInterrupted();
        try {
            new ThreadTest4().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ThreadTest4().notify();*/
        ThreadTest4 t4 = new ThreadTest4();
        new Thread(() -> {
            try {
                t4.test1a();
            } catch (InterruptedException e) {
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (t4){
            t4.notify();//wait notify notifyAll必须在同步代码块内执行
        }
        //new Thread(.set)
    }

    private void test1a() throws InterruptedException {
        synchronized (this) {
            System.out.println("wait");
            this.wait();
            System.out.println("notify");
        }
    }
}
