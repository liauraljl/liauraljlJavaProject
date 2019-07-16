package test.ThreadTest;

/**
 * Created by liaura_ljl on 2018/12/9.
 */
public class ThreadTest3 implements Runnable {
    public  static void main(String[] args){
        Thread t1=new Thread(new ThreadTest3());
        t1.start();
    }

    @Override
    public void run(){
        while (true){
            System.out.println("Oh,I am Runnable!");
        }
    }
}
