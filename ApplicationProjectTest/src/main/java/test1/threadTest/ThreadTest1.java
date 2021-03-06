package test1.threadTest;

/**
 * Created by liaura_ljl on 2018/3/24.
 */
public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1= new Thread(() -> {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                    Thread.interrupted();
                    //break;
                }else{
                    System.out.println(System.currentTimeMillis());
                }
                //Thread.yield();
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        //Thread.interrupted();
    }
}
