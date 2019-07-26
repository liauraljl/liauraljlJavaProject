package test1.ThreadTest;

/**
 * Created by liaura_ljl on 2018/12/9.
 */
public class ThreadTest2 {
    public static void main(String[] args){
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
                    System.out.println("Hello ,I am t1");
                }
            }
        };
        t1.start();
    }
}
