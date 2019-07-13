package test.ThreadTest;

/**
 * Created by liaura_ljl on 2019/2/28.
 */
public class ThreadGroup20190228 implements Runnable {
    public static void main(String[] args){
        ThreadGroup tg=new ThreadGroup("PrintGroup");
        Thread t1=new Thread(tg,new ThreadGroup20190228(),"T1");
        Thread t2=new Thread(tg,new ThreadGroup20190228(),"T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }

    @Override
    public void run() {
        String groupAndName= Thread.currentThread().getThreadGroup().getName()+"-"+ Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
