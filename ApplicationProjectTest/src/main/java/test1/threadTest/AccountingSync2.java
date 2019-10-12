package test1.threadTest;

/**
 * 实例锁
 * Created by liaura_ljl on 2019/2/28.
 */

public class AccountingSync2 implements Runnable {
    static AccountingSync2 instance=new AccountingSync2();
    static int i=0;
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<10000000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);*/
        Thread t1=new Thread(new AccountingSync2());
        Thread t2=new Thread(new AccountingSync2());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
